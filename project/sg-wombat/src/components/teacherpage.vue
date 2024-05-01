<template>
    <div class="outcontainer">
        <div class="innercontainer">
            <img src="https://images.squarespace-cdn.com/content/v1/5dc2cd8defa71b41e983be85/1573049395906-VZ2UOOHN6KX47VYPAA11/logo.png?format=750w"
                alt="Logo" class="logo">
            <el-container>
                <el-header>Booking Details</el-header>

                <div class="smallhead">
                    Teacher and School Details
                </div>

                <el-main>
                    <el-form :model="form" :rules="rules" label-position="top" class="booking-form" ref="formRef">

                        <el-row :gutter="150">
                            <el-col :span="10">
                                <el-form-item label="First Name" prop="firstName">
                                    <el-input placeholder="First Name" v-model="form.firstName">></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="10" class="input1">
                                <el-form-item label="Last Name" prop="lastName">
                                    <el-input placeholder="Last Name" v-model="form.lastName"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-form-item label="School" prop="school">
                            <el-input placeholder="School" v-model="form.school"></el-input>
                        </el-form-item>
                        <el-form-item label="Email address" prop="email">
                            <el-input placeholder="Email" v-model="form.email"></el-input>
                        </el-form-item>
                        <el-form-item label="Mobile number" prop="mobileNumber">
                            <el-input placeholder="Mobile number" v-model="form.mobileNumber"></el-input>
                        </el-form-item>
                        <el-form-item label="Teaching area" prop="teachingArea">
                            <el-input placeholder="Teaching area" v-model="form.teachingArea"></el-input>
                        </el-form-item>
                        <el-form-item label="Visit date" prop="visitDate">
                            <el-date-picker v-model="form.visitDate" :disabled-date="disabledDate" type="date"
                                placeholder="Select Date">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item prop="datePreference">
                            <div class="question">
                                What is your first date preference?
                                <span class="note">
                                    Please note: Workshop availability for Term 1 and 2 is limited.
                                    Please contact our team to discuss on 03 8344 1420 or via email:
                                    <a href="mailto:school-bookings@unimelb.edu.au">school-bookings@unimelb.edu.au</a>.
                                </span>
                            </div>
                            <el-form-item label="First Preference Visit date" prop="datePreference">
                                <el-date-picker v-model="form.datePreference" :disabled-date="disabledDate" type="date"
                                    placeholder="Select Date">
                                </el-date-picker>
                            </el-form-item>
                        </el-form-item>



                    </el-form>
                    <el-button class="buttom" type="primary" @click="submitForm">Next</el-button>

                </el-main>
            </el-container>

        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

// 数据属性
const form = ref({
    firstName: '',
    lastName: '',
    school: '',
    email: '',
    mobileNumber: '',
    teachingArea: '',
    visitDate: '',
    datePreference: '',
});

// 验证规则
const rules = ref({
    firstName: [{ required: true, message: 'First name is required', trigger: 'blur' }],
    lastName: [{ required: true, message: 'Last name is required', trigger: 'blur' }],
    school: [{ required: true, message: 'School name is required', trigger: 'blur' }],
    email: [{ required: true, message: 'Email is required', trigger: 'blur' }],
    mobileNumber: [{ required: true, message: 'Mobile Number is required', trigger: 'blur' }],
    teachingArea: [{ required: true, message: 'Teaching Are is required', trigger: 'blur' }],
    visitDate: [{ required: true, message: 'Visit Date is required', trigger: 'blur' }],
    datePreference: [{ required: true, message: 'Preference Data is required', trigger: 'blur' }],
    // ...其他字段的规则
});

import {
    ElForm,
    ElFormItem,
    ElInput,
    ElRadioGroup,
    ElRadio,
    ElDatePicker,
    ElButton,
    ElRow,
    ElCol
} from 'element-plus';



import { useRouter } from 'vue-router'

const router = useRouter()

const formRef = ref(null);

// 在这里定义 goToTargetPage 函数
function goToTargetPage() {
    // 使用 router.push 方法来导航
    router.push({ name: 'teacherpage2' }); // 确保你的路由配置中有对应的路由
}

const disabledDate = (time) => {
    // 获取日期是星期几（0 是星期日，1 是星期一，...，6 是星期六）
    const dayOfWeek = new Date(time).getDay();
    // 如果是星期一(1)、星期六(6)或星期日(0)，则禁用
    return dayOfWeek === 1 || dayOfWeek === 6 || dayOfWeek === 0;
};



import { ElMessageBox, ElMessage } from 'element-plus';

const submitForm = () => {
    formRef.value.validate((valid) => {
        if (valid) {
            ElMessageBox.confirm(
                'Do you want to go to next page?',
                'Confirm Submission',
                {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning',
                }
            ).then(() => {
                ElMessage({
                    type: 'success',
                    message: 'Submission Success',
                });
                // 如果确认提交，执行页面跳转
                goToTargetPage();
            }).catch(() => {
                ElMessage({
                    type: 'info',
                    message: 'Submission Canceled',
                });
            });
        } else {
            ElMessage.error('Please correct the errors in the form.');
        }
    });
};
</script>


<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

.outcontainer {

    max-width: 1920px;
    max-height: 1080px;
    width: 1920px;
    height: 1080px;
    background-color: #2E4DD4;
    justify-content: center;

    display: flex;
    font-family: 'Poppins', sans-serif;
}

.innercontainer {
    width: 70%;
    height: 100%;
    background-color: #eef1f6;


}

.logo {
    max-width: 200px;
    /* 或设置为固定的宽度 */
    height: auto;
    margin-left: 1000px;
    margin-top: 3%;
    /* 保持图片的纵横比 */
    /* 根据需要添加其他样式，比如padding或margin来定位图片 */
}

.el-container {
    margin-top: -150px
}

.booking-form {
    max-width: 90%;
    margin-top: -20px;
    font-size: 20px;
    height: 700px;
    /* 设置一个合适的高度 */
    overflow-y: auto;
    /* 当内容超过高度时显示垂直滚动条 */
    overflow-x: hidden;
    /* 阻止水平滚动 */
}

.danxuan {
    /* background-color: yellow; */
    margin-left: 20px;
}



.buttom {
    /* background-color: #2E4DD4; */
    width: 100px;
    text-align: left;
    padding: 20px;
    margin-left: 950px;
    margin-top: 50px;
    font-size: 20px
}

.smallhead {
    /* background-color: aqua; */
    width: 400px;
    text-align: left;
    padding: 20px;
    margin-left: 100px;
    font-size: 20px;

    margin-top: 20px;
}

.el-header {
    /* background-color: blueviolet; */
    color: #2E4DD4;
    width: 400px;
    text-align: left;
    margin-left: 100px;
    margin-top: 3%;
    padding: 30px 20px;
    font-weight: bolder;
    font-size: 40px;
}

.el-main {
    margin-left: 100px;
    margin-top: 10px;
    padding: 20px;
}

.el-form-item {
    margin-bottom: 25px;
}

.question {
    text-align: left;
    font-size: 18px;
    margin-bottom: 15px;
}

.note {
    display: block;
    text-align: left;
    font-size: 14px;
    color: #999;
    margin-top: 5px;
}

a {
    color: #0078d4;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

.el-radio-group {
    display: flex;
    flex-direction: column;
}


.el-radio {


    /* 这将对齐单选按钮和文本 */
    margin-bottom: 10px;
}
</style>