<template>
    <div class="container">
        <NavigationBar class="navigation-panel" />
        <div class="dashboard-content">
            <BookingDetail class="listbox" />
            <!-- 添加触发弹窗的按钮 -->
            <el-button @click="showAddNewProgramForm">Add New Program</el-button>
            <!-- addNewProgram 组件实例，通过 form-visible 属性控制显示 -->
            <addNewProgram :form-visible.sync="isFormVisible" />
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
    border-radius: 30px;
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
    border-radius: 30px;
}

.listbox {
    margin-top: 50px;
    margin-left: 30px;
    background-color: white;
    width: 1000px;
    height: 900px;
}


</style>



<script>
import NavigationBar from './newbar.vue'; // Ensure the import name matches the convention
import BookingDetail from './bookingdetail.vue';
import addNewProgram from './addNewProgram.vue';


export default {
    components: {
        NavigationBar, // Correct naming to match the import
        BookingDetail,
        addNewProgram
    },
    data() {
        return {
            isFormVisible: false, // 控制表单弹窗的显示状态
        };
    },
    methods: {
        showAddNewProgramForm() {
            console.log("Attempting to show form");
            this.isFormVisible = true; // 方法用来显示表单
            console.log("Form visibility should now be true:", this.isFormVisible);
        }
    }
}
</script>