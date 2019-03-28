const {resolve} = require('path');
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
const CopyPlugin = require('copy-webpack-plugin');


module.exports = {
    baseUrl: './',
    outputDir: './src/main/resources/ui',
    assetsDir: 'assets',
    pages: {
        'sba-core': {
            entry: './src/main/frontend/index.js',
            template: './src/main/frontend/index.html',
            filename: 'index.html'
        },
        'login': {
            entry: './src/main/frontend/login.js',
            template: './src/main/frontend/login.html',
            filename: 'login.html'
        }
    },
    chainWebpack: config = > {
    config.resolve.alias.set('@', resolve(__dirname, 'src/main/frontend'));
config.module.rule('html')
    .test(/\.html$/)
    .use('html-loader')
    .loader('html-loader')
    .options({
        root: resolve(__dirname, 'src/main/frontend'),
        attrs: []
    })
    .end();
config.plugin('prefetch-sba-core')
    .tap(args = > {
    args[0].fileBlacklist = [/\.map$/, /event-source-polyfill\.?[a-z0-9]*\.js/];
return args;
})
;
config.plugin('preload-login')
    .tap(args = > {
    args[0].include.entries = [];
return args;
})
;
config.plugin('prefetch-login')
    .tap(args = > {
    args[0].include.entries = [];
return args;
})
;
config.plugin('define')
    .tap(args = > {
    args[0]['__PROJECT_VERSION__'] = `'${process.env.PROJECT_VERSION || ''}'`;
return args;
})
;
},
configureWebpack: {
    plugins: [
        new CopyPlugin([{
            from: resolve(__dirname, 'src/main/frontend/assets'),
            to: resolve(__dirname, './src/main/resources/ui/assets'),
            toType: 'dir',
            ignore: ['*.scss']
        }]),
        new BundleAnalyzerPlugin({
            analyzerMode: 'static',
            openAnalyzer: false,
            reportFilename: '../report.html'
        })
    ]
}
}
;
