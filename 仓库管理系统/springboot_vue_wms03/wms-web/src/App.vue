<template>
    <div id="app">
        <router-view/>
    </div>

</template>

<script>
    export default {
        name: 'App',
        components: {},
        data() {
            return {
                user: JSON.parse(sessionStorage.getItem('CurUser')),
            }
        },
        watch: {
            '$store.state.menu': {
                //当前值，旧值
                handler(val, old) {
                    //假如旧的值不见了，但是用户账号有
                    console.log(val)
                    if (!old && this.user && this.user.id) {
                        //再重新取到值val去，调用setMenu数据
                        this.$store.commit("setRouter", val)
                    }
                },
                immediate: true
            }
        }
    }
</script>

<style>
    #app {
        height: 100%;
    }
</style>
