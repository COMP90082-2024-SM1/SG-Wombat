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
                    <div class="dashboard-content">
                        <div class="HeadTitle">
                            <el-menu mode="horizontal" :popper-offset="16">
                                <el-sub-menu index="1">
                                    <template #title>All Bookings</template>
                                    <el-menu-item index="1-1">Confirmed Bookings</el-menu-item>
                                    <el-menu-item index="1-2">Other Bookings</el-menu-item>
                                </el-sub-menu>
                            </el-menu>
                        </div>

                        <BookingDetail class="listbox" />
                    </div>
                </el-card>

            </el-main>

            <el-footer>
                Science Gallery Booking Management System @2024 Created by Unimelb Team Wombat
            </el-footer>

        </el-container>

    </el-container>
</template>


<script>
import { ArrowDown } from '@element-plus/icons-vue'
import navigationMenu from './navBarNew.vue';
import BookingDetail from './bookingdetail.vue';

export default {
    components: {
        navigationMenu,
        BookingDetail,
    }
}


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
    /* padding-left: 20px; */
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

.layout-container .el-main {
    /* background-color: lightblue; */
    /* border-radius: 30px; */

}

.science {

    color: white;
    font-size: 40px;
    font-weight: bold;
    margin-top: 30px;
    margin-bottom: 20px;
    /* width: 50%; */
    /* margin-right: auto; */
    /* margin-left: 30px; */
    /* background-color: blueviolet; */
    /* width: 330px; */
    /* height: 56px; */
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.main-content {

    z-index: 1001;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;

}
</style>