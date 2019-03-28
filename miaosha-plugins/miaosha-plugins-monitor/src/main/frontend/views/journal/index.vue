<!--
  - Copyright 2014-2018 the original author or authors.
  -
  - Licensed under the Apache License, Version 2.0 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     http://www.apache.org/licenses/LICENSE-2.0
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<template>
    <div class="section">
        <div class="container">
            <div v-if="error" class="message is-warning">
                <div class="message-body">
                    <strong>
                        <font-awesome-icon class="has-text-warning" icon="exclamation-triangle"/>
                        Server connection failed.
                    </strong>
                    <p v-text="error.message"/>
                </div>
            </div>
            <table class="table is-fullwidth">
                <thead>
                <tr>
                    <th>应用</th>
                    <th>实例</th>
                    <th>时间</th>
                    <th>事件</th>
                </tr>
                </thead>
                <tbody>
                <template v-for="event in events">
                    <tr class="is-selectable" :key="event.key"
                        @click="showPayload[event.key] ? $delete(showPayload, event.key) : $set(showPayload, event.key, true)">
                        <td v-text="instanceNames[event.instance] || '?'"/>
                        <td v-text="event.instance"/>
                        <td v-text="event.timestamp.format('L HH:mm:ss.SSS')"/>
                        <td>
                            <el-tag type="warning" v-if="event.type === 'DEREGISTERED'">取消注册</el-tag>
                            <el-tag v-if="event.type === 'REGISTERED'">服务注册</el-tag>
                            <el-tag v-if="event.type === 'REGISTRATION_UPDATED'">注册更新</el-tag>
                            <el-tag type="info" v-if="event.type === 'ENDPOINTS_DETECTED'">端点检查</el-tag>
                            <el-tag type="success"
                                    v-if="event.type === 'STATUS_CHANGED' && event.payload.statusInfo.status === 'UP'">
                                服务上线
                            </el-tag>
                            <el-tag type="warning"
                                    v-if="event.type === 'STATUS_CHANGED' && event.payload.statusInfo.status === 'DOWN'">
                                服务下线
                            </el-tag>
                            <el-tag type="danger"
                                    v-if="event.type === 'STATUS_CHANGED' && event.payload.statusInfo.status === 'OFFLINE'">
                                服务注销
                            </el-tag>
                        </td>
                    </tr>
                    <tr :key="`${event.key}-detail`" v-if="showPayload[event.key]">
                        <td colspan="4">
                            <pre class="is-breakable" v-text="toJson(event.payload)"/>
                        </td>
                    </tr>
                </template>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    import subscribing from '@/mixins/subscribing';
    import Instance from '@/services/instance';
    import {compareBy} from '@/utils/collections';
    import omit from 'lodash/omit';
    import moment from 'moment';

    class Event {
        constructor(event) {
            this.instance = event.instance;
            this.version = event.version;
            this.type = event.type;
            this.timestamp = moment(event.timestamp);
            this.payload = omit(event, ['instance', 'version', 'timestamp', 'type']);
        }

        get key() {
            return `${this.instance}-${this.version}`;
        }
    }

    export default {
        mixins: [subscribing],
        data: () => ({
            events: [],
            showPayload: {},
            instanceNames: {},
            error: null
        }),
        methods: {
            toJson(obj) {
                return JSON.stringify(obj, null, 4);
            },
            addEvents(data) {
                const converted = data.map(event => new Event(event));
                converted.reverse();
                this.events = converted.concat(this.events);

                const newInstanceNames = converted.filter(event => event.type === 'REGISTERED').reduce((names, event) => ({
                    ...names,
                    [event.instance]: event.payload.registration.name
                }), {});
                Object.assign(this.instanceNames, newInstanceNames);
            },
            createSubscription() {
                return Instance.getEventStream().subscribe({
                    next: message => {
                        this.error = null;
                        this.addEvents([message.data])
                    },
                    error: error => {
                        console.warn('Listening for events failed:', error);
                        this.error = error;
                    }
                });
            }
        },
        async created() {
            try {
                const response = await Instance.fetchEvents();
                this.addEvents(response.data.sort(compareBy(v => v.timestamp)));
                this.error = null;
            } catch (error) {
                console.warn('Fetching events failed:', error);
                this.error = error;
            }
        },
        install({viewRegistry}) {
            viewRegistry.addView({
                path: '/journal',
                name: 'journal',
                label: '事件',
                order: 100,
                component: this
            });
        }
    };
</script>
