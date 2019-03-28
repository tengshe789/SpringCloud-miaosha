<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="${basePath}">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hystrix Dashboard</title>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
</head>
<body>
<div id="app">
    <el-container>
        <el-main>
            <div style="width:600px;margin:0 auto;">
                <center>
                    <img width="264" height="233" src="<@spring.url '/hystrix'/>/images/hystrix-logo.png">
                    <br>
                    <br>

                    <h2>Hystrix Dashboard</h2>
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="stream">
                            <el-input v-model="form.stream"></el-input>
                        </el-form-item>
                        <el-form-item label="delay">
                            <el-input v-model="form.delay"></el-input>
                        </el-form-item>
                        <el-form-item label="title">
                            <el-input v-model="form.title"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="sendToMonitor">开始监控</el-button>
                        </el-form-item>
                    </el-form>
                </center>
            </div>
        </el-main>
    </el-container>
</div>
</body>
</html>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
    new Vue({
        el: '#app',
        data() {
            return {
                form: {
                    stream: '',
                    delay: '',
                    title: '',
                }
            }
        },
        methods: {
            sendToMonitor() {
                if ($('#stream').val().length > 0) {
                    var url = "<@spring.url '/hystrix'/>/monitor?stream=" + encodeURIComponent($('#stream').val()) + "";
                    if ($('#delay').val().length > 0) {
                        url += "&delay=" + $('#delay').val();
                    }
                    if ($('#title').val().length > 0) {
                        url += "&title=" + encodeURIComponent($('#title').val());
                    }
                    location.href = url;
                } else {
                    this.$message({
                        message: "The 'stream' value is required.",
                        type: 'warning'
                    });
                }
            }
        }
    })
</script>
