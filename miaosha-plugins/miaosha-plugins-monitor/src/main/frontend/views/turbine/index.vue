<!--
  -    Copyright (c) 2018-2025, lengleng All rights reserved.
  -
  - Redistribution and use in source and binary forms, with or without
  - modification, are permitted provided that the following conditions are met:
  -
  - Redistributions of source code must retain the above copyright notice,
  - this list of conditions and the following disclaimer.
  - Redistributions in binary form must reproduce the above copyright
  - notice, this list of conditions and the following disclaimer in the
  - documentation and/or other materials provided with the distribution.
  - Neither the name of the pig4cloud.com developer nor the names of its
  - contributors may be used to endorse or promote products derived from
  - this software without specific prior written permission.
  - Author: lengleng (wangiegie@gmail.com)
  -->

<template>
    <div class="section">
        <div class="container">
            <div style="width:800px;margin:0 auto;">
                <center>
                    <img width="264" height="233" src="hystrix/images/hystrix-logo.png">
                    <br>
                    <br>
                    <h2>PigX Hystrix Dashboard</h2>
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="目标流">
                            <el-input v-model="form.stream"></el-input>
                        </el-form-item>
                        <el-form-item label="延迟">
                            <el-input type="number" v-model="form.delay"></el-input>
                        </el-form-item>
                        <el-form-item label="标题">
                            <el-input v-model="form.title" t></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="sendToMonitor">开始监控</el-button>
                        </el-form-item>
                    </el-form>
                </center>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data: () => ({
            // eslint-disable-next-line no-undef
            version: __PROJECT_VERSION__,
            form: {
                stream: 'http://localhost:5001/turbine.stream',
                delay: '',
                title: ''
            }
        }),
        methods: {
            sendToMonitor() {
                if (this.form.stream) {
                    let url = "hystrix/monitor?stream=" + encodeURIComponent(this.form.stream) + "";
                    if (this.form.delay) {
                        url += "&delay=" + this.form.delay;
                    }
                    if (this.form.title) {
                        url += "&title=" + encodeURIComponent(this.form.title);
                    }
                    location.href = url;
                } else {
                    this.$message({
                        message: "The 'stream' value is required.",
                        type: 'warning'
                    });
                }
            }
        },
        install({viewRegistry}) {
            viewRegistry.addView({
                path: '/turbine',
                name: 'turbine',
                label: '实时监控',
                order: 200,
                component: this
            });
        }
    };
</script>
<style>
    .hystrix {
        width: 100%;
        height: 800px;
    }
</style>
