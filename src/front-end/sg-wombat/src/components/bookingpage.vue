<template>
    <div class="container">
        <NavigationBar class="navigation-panel" />
        <div class="dashboard-content">
            <div class="HeadTitle">
                <el-menu mode="horizontal" :popper-offset="16">
                    <el-sub-menu index="1">
                        <template #title>All Booking</template>
                        <el-menu-item index="1-1">item one</el-menu-item>
                        <el-menu-item index="1-2">item two</el-menu-item>
                        <el-menu-item index="1-3">item three</el-menu-item>
                    </el-sub-menu>
                </el-menu>
            </div>

            <BookingDetail class="listbox" />
        </div>
    </div>
</template>

<style scoped>
.HeadTitle {
    width: 1000px;
    height: 100px;
    background-color: aqua;
    margin-left: 20px;
    align-items: center;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
    font-size: 30px;
}

.block-col-2 .demonstration {
    display: block;
    color: var(--el-text-color-secondary);
    font-size: 30px;
    margin-bottom: 20px;
}

.block-col-2 .el-dropdown-link {
    display: flex;
    align-items: center;
}

.container {
    width: 100%;
    display: flex;
    flex-direction: row;
    height: 100vh;
    min-width: 1350px;
    /* border-radius: 30px */
}


.navigation-panel {
    /* position: absolute; */
    top: 0;
    left: 0;
    width: 30%;
    height: 100%;
    z-index: 1000;
}

.dashboard-content {

    background-color: green;
    height: 100vh;

    width: 60%;

    display: flex;
    flex-grow: 1;
    z-index: 1001;
}

.listbox {
    margin-top: 50px;
    margin-left: 30px;
    background-color: yellow;
    width: 1000px;
    height: 900px;
}
</style>



<script>
import { ArrowDown } from '@element-plus/icons-vue'
import NavigationBar from './newbar.vue'; // Ensure the import name matches the convention
import BookingDetail from './bookingdetail.vue';

export default {
    components: {
        NavigationBar, // Correct naming to match the import
        BookingDetail,
    }
}


</script>