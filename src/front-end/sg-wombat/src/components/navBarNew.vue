<template>
    <el-menu :default-active="selectedIndex" @select='selected' background-color='#2E4DD4' active-text-color='#ffd04b'
        router>
        <el-menu-item class="innerbox" index='/dashboard'>

            <el-icon size="40" color="white" opacity=0.6>
                <HomeFilled />
            </el-icon>

            <div class="text">
                Dashboard
            </div>
        </el-menu-item>

        <!-- <el-sub-menu>

        </el-sub-menu> -->

        <el-menu-item class="innerbox" index='/booking' @click="activate('bookingpage')">
            <el-icon size="40" color="white">
                <List />
            </el-icon>


            <div class="text">
                Booking
            </div>

        </el-menu-item>
        <el-menu-item class="innerbox" index='3' @click="activate('programpage')">
            <el-icon size="40" color="white">
                <Opportunity />
            </el-icon>

            <div class="text">
                Programs
            </div>
        </el-menu-item>
        <el-menu-item class="innerbox" index='4' @click="activate('roompage')">
            <el-icon size="40" color="white">
                <Place />
            </el-icon>

            <div class="text">
                Schools
            </div>
        </el-menu-item>

        <el-menu-item class="innerbox" index='6' plain @click="open">

            <el-icon size="40" color="white">
                <Share />
            </el-icon>

            <div class="text" size="40" color="white">
                Export Report
            </div>
        </el-menu-item>
    </el-menu>


</template>

<script setup>
import { ElMessage, ElMessageBox } from 'element-plus';
import { watch, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const router = useRouter();
const route = useRoute();

const selectedIndex = ref('1');

// Watch for route changes to update the active menu item
watch(() => route.name, (newRouteName) => {
    switch (newRouteName) {
        case 'dashboardPage':
            selectedIndex.value = '/dashboard';
            break;
        case 'bookingpage':
            selectedIndex.value = '/booking';
            break;
        case 'programpage':
            selectedIndex.value = '3';
            break;
        case 'roompage':
            selectedIndex.value = '4';
            break;
        case 'schoolpage':
            selectedIndex.value = '5';
            break;
        default:
            selectedIndex.value = '1';
            break;
    }
}, { immediate: true });

const open = () => {
    ElMessageBox.confirm(
        'Do you want to export the report, Continue?',
        'Notice',
        {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'info',
        }
    ).then(() => {
        ElMessage({
            type: 'success',
            message: 'Export Success',
        });
    }).catch(() => {
        ElMessage({
            type: 'info',
            message: 'Export Canceled',
        });
    });
};

function activate(routeName) {
    router.push({ name: routeName });
}
</script>


<style scoped>
/* @import '../assets/navigation_bar.css'; */
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

.el-menu {
    border-right: none;
}

.icon {
    size: 50;
    /* margin-left: 20px; */
    text-align: center;
    justify-content: center;
    color: white;
    opacity: 0.6;
}

.text {
    font-size: 22px;
    margin-left: 10px;
    /* background-color: orange; */
    width: 80%;
    height: 80px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    opacity: 1;
}

.innerbox {
    color: white;
    font-size: 100px;
    /* margin-top: 10px; */
    /* width: 20%; */
    background-color: #2E4DD4;
    /* width: 330px; */
    height: 80px;
    display: flex;
    justify-content: center;
}
</style>
