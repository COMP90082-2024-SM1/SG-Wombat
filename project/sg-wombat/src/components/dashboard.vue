<template>
    <div class="container">
        <navigation_bar class="navigation-panel" />
        <div class="dashboard-content">
            <!-- Dashboard的其余内容 -->
            <div>Dashboard Content Here</div>
        </div>
    </div>
</template>

<style scoped>
.container {
    position: relative;
    width: 100%;
    display: flex;
    flex-direction: row;
    min-height: 1080px;
    border-radius: 30px
}

.navigation-panel {
    position: absolute;
    top: 0;
    left: 0;
    width: 30%;
    height: 100%;
    z-index: 1000;


}

.dashboard-content {
    background-color: white;
    height: 1080px;
    width: 920px;
    width: 70%;
    margin-left: 400px;
    display: flex;
    flex-grow: 1;
    z-index: 1001;
    border-radius: 30px
}
</style>



<script>
import navigation_bar from './navigationbar.vue'; // 确保路径正确

export default {
    components: {
        navigation_bar
    }
}


</script>