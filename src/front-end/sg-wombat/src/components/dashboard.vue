<template>
    <el-container class="layout-container">
        <!-- navigation sidebar -->
        <el-aside width="400px">
            <div class="science">
                Science Gallery
            </div>
            <!-- el-menu -->
            <navigationMenu class="navigation-panel" />
        </el-aside>

        <!-- main content area on the right side-->
        <el-container class="main-content">
            <!-- header area -->
            <el-header>
                <div>Booking Management System</div>
                <!-- user logout menu -->
                <el-dropdown>
                    <span class="el-dropdown-link">
                        Admin User
                        <el-icon class="el-icon--right">
                            <arrow-down />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item>Logout</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-header>

            <!-- main content -->
            <el-main>
                <el-card>
                    <Calendar class="calendarbox" />
                </el-card>

            </el-main>

            <el-footer>
                Science Gallery Booking Management System @2024 Created by Unimelb Team Wombat
            </el-footer>

        </el-container>

    </el-container>
</template>

<!-- <template>
    <div class="container">
        <NavigationBar class="navigation-panel" />
        <div class="dashboard-content">

            <Calendar class="calendarbox" />

        </div>
    </div>
</template> -->

<!-- <style scoped>
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
    /* background-color: green; */

    height: 100vh;

    width: 60%;
   
    display: flex;
    flex-grow: 1;
    z-index: 1001;
    /* border-radius: 30px */
}

.calendarbox {
    margin-top: 50px;
    margin-left: 30px;
    background-color: white;
   
    height: 80vh;
}
</style> -->

<script lang="ts">
// import NavigationBar from './newbar.vue'; // Ensure the import name matches the convention
import Calendar from './calendar.vue'; // Ensure the import name matches the convention

export default {
    components: {
        // NavigationBar, // Correct naming to match the import
        Calendar
    }
}



</script>

<script lang="ts" setup>
import { ArrowDown } from '@element-plus/icons-vue'
import navigationMenu from './navBarNew.vue';

</script>

<style scoped>
.layout-container {
    height: 100vh;
    position: relative;
    /* width: 100vw;
    display: flex;
    flex-direction: row; */
    /* border-radius: 30px; */
}


.navigation-panel {
    z-index: 1000;
}

.layout-container .el-aside {
    background-color: #2E4DD4;
    color: #fff;
    border-radius: 30px;
    font-family: 'Poppins', sans-serif;
}

.layout-container .el-header {
    font-size: 30px;
    margin-top: 30px;
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.layout-container .el-footer {
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: center;
    color: grey;
    font-size: 15px;
}

.science {

    color: white;
    font-size: 40px;
    font-weight: bold;
    margin-top: 30px;
    margin-bottom: 20px;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.main-content {
    z-index: 1001;
}
</style>