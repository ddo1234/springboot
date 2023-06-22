const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    //设置是否在开发环境下每次保存代码时都启用 eslint验证。
    // =>解决问题：
    // should always be multi-word  vue/multi-word-component-names
    // Component name "Login" should always be multi-word  vue/multi-word-component-names
    lintOnSave:false,
    chainWebpack: config => {
        config.resolve.alias.set('vue-i18n', 'vue-i18n/dist/vue-i18n.cjs.js')
    }

})

