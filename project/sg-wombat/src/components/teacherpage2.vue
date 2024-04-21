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
                    <el-form label-position="top" class="booking-form">
                        <el-row :gutter="150">
                            <el-col :span="10">
                                <el-form-item label="First Name">
                                    <el-input placeholder="First Name" v-model="firstName"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="10" class="input1">
                                <el-form-item label="Last Name">
                                    <el-input placeholder="Last Name" v-model="lastName"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-form-item label="School">
                            <el-input placeholder="School" v-model="school"></el-input>
                        </el-form-item>
                        <el-form-item label="Email address">
                            <el-input placeholder="Email" v-model="email"></el-input>
                        </el-form-item>
                        <el-form-item label="Mobile number">
                            <el-input placeholder="Mobile number" v-model="mobileNumber"></el-input>
                        </el-form-item>
                        <el-form-item label="Teaching area">
                            <el-input placeholder="Teaching area" v-model="teachingArea"></el-input>
                        </el-form-item>
                        <el-form-item label="Visit date">
                            <el-date-picker placeholder="Visit date" v-model="visitDate"></el-date-picker>
                        </el-form-item>
                        <el-form-item>
                            <div class="question">
                                What is your first date preference?
                                <span class="note">
                                    Please note: Workshop availability for Term 1 and 2 is limited.
                                    Please contact our team to discuss on 03 8344 1420 or via email:
                                    <a href="mailto:school-bookings@unimelb.edu.au">school-bookings@unimelb.edu.au</a>.
                                </span>
                            </div>
                            <el-radio-group v-model="datePreference">
                                <div class='danxuan'>
                                    <el-radio label="2024-03-05">Tuesday 5 March 2024</el-radio>
                                </div>
                                <div class='danxuan'><el-radio label="2024-03-12">Tuesday 12 March 2024</el-radio></div>
                                <div class='danxuan'>
                                    <el-radio label="2024-04-23">Tuesday 23 April 2024</el-radio>
                                </div>
                                <div class='danxuan'><el-radio label="2024-05-14">Tuesday 14 May 2024</el-radio></div>
                                <div class='danxuan'><el-radio label="2024-05-21">Tuesday 21 May 2024</el-radio></div>
                                <div class='danxuan'><el-radio label="2024-06-04">Tuesday 4 June 2024</el-radio></div>
                                <div class='danxuan'><el-radio label="2024-06-18">Tuesday 18 June 2024</el-radio></div>

                            </el-radio-group>
                        </el-form-item>



                    </el-form>
                    <el-button class="buttom" type="primary">Next</el-button>
                </el-main>
            </el-container>

        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
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

// 定义数据属性，用于与输入框进行双向绑定
const firstName = ref('');
const lastName = ref('');
const school = ref('');
const email = ref('');
const mobileNumber = ref('');
const teachingArea = ref('');
const visitDate = ref('');
const datePreference = ref('');

</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

.outcontainer {

    max-width: 1920px;
    max-height: 1080px;
    width: 1920px;
    height: 1080px;
    background-color: #2E4DD4;
    justify-content: center;
    text-align: center;
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

.el-container{
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
    background-color: yellow;
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
    font-size: 20px
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

.el-radio__label {
    margin-left: 8px;
    /* 可能需要调整以确保对齐 */
    font-size: 18px;
    /* 字体大小与问题文本一致 */
    line-height: 1.5;
}

.el-radio__input .el-radio__inner {
    margin-right: 8px;
    /* 和label的间距一致 */
    /* 如果需要，调整单选按钮大小 */
}
</style>