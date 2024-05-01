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


                        <el-form-item label="8. Which program are you interested in?" prop="selectedProgram">
                            <el-select v-model="form.selectedProgram" placeholder="Select a program">
                                <el-option
                                    label="Half day experience with two modules: NOT NATURA TOUR + (UN)EXPECTED WORKSHOP at 11am-2:15pm (with a 45min lunch break)"
                                    value="halfDayTwoModules"></el-option>
                                <el-option
                                    label="Half day experience with two modules: NOT NATURAL TOUR + CHICKENOSAURUS WORKSHOP at 11am-2:15pm (with a 45min lunch break)"
                                    value="halfDayTwoModulesChicken"></el-option>
                                <el-option
                                    label="Full day experience with three modules: NOT NATURAL TOUR + (UN)EXPECTED WORKSHOP + CHICKENOSAURUS WORKSHOP at 9:30am-2:15pm (with a 15min recess and a 45min lunch break)"
                                    value="fullDayThreeModules"></el-option>
                            </el-select>
                        </el-form-item>



                        <el-form-item
                            label="9. What is your second date preference? Please note: Workshop availability for Term 1 and 2 is limited. Please contact our team to discuss on 03 8344 1420 or via email: school-bookings@unimelb.edu.au."
                            prop="datePreference2">
                            
                                <el-date-picker v-model="form.datePreference2" :disabled-date="disabledDate" type="date"
                                    placeholder="Select Date">
                                </el-date-picker>
                        </el-form-item>

                        <el-form-item label="10. Approximately how many students will be attending?"
                            prop="studentCount">
                            <el-input-number v-model.number="form.studentCount" :min="20" :max="50"></el-input-number>
                            <div>Note: minimum 20 students, maximum 50 students</div>
                        </el-form-item>


                        <!-- Question 11 -->
                        <el-form-item label="11. What are the student level(s)?" prop="studentLevels">
                            <el-checkbox-group v-model="form.studentLevels">
                                <el-checkbox label="7">7</el-checkbox>
                                <el-checkbox label="8">8</el-checkbox>
                                <el-checkbox label="9">9</el-checkbox>
                                <el-checkbox label="10">10</el-checkbox>
                                <el-checkbox label="11">11</el-checkbox>
                                <el-checkbox label="12">12</el-checkbox>

                                <el-checkbox label="VCE">VCE</el-checkbox>
                                <el-checkbox label="VCE Vocational Major or VET">VCE Vocational Major or
                                    VET</el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>

                        <!-- Question 12 -->
                        <el-form-item label="12. What learning area or subject is the focus of this group?"
                            prop="learningArea">
                            <el-input v-model="form.learningArea" placeholder="输入你的答案"></el-input>
                        </el-form-item>

                        <el-form-item label="13. Are you a low-SES (ICSEA<1000) School?" prop="isLowSES">
                            <el-radio-group v-model="form.isLowSES">
                                <el-radio :label="true">Yes</el-radio>
                                <el-radio :label="false">No</el-radio>
                            </el-radio-group>

                        </el-form-item>

                        <!-- Question 14 -->
                        <el-form-item label="14. What is the school's ABN number?" prop="abnNumber">
                            <el-input v-model="form.abnNumber" placeholder="Enter your answer"></el-input>
                        </el-form-item>

                        <!-- Question 15 -->
                        <el-form-item
                            label="15. Does your student cohort have any specific needs that we should be aware of?"
                            prop="specificNeeds">
                            <el-input type="textarea" v-model="form.specificNeeds"
                                placeholder="This could be accessibility or sensory needs, or it could be that attending students need a prayer room during breaks."></el-input>
                        </el-form-item>

                        <!-- Question 16 -->
                        <el-form-item label="16. Anything else you want us to know?" prop="additionalInfo">
                            <el-input type="textarea" v-model="form.additionalInfo"
                                placeholder="Please note here if the organising teacher is different to the teacher attending the program"></el-input>
                        </el-form-item>

                        <el-form-item label="17. Can we sign you up to our SGM Excursions mailing list?"
                            prop="mailingListSignup">
                            <el-radio-group v-model="form.mailingListSignup">
                                <el-radio :label="true">Yes please</el-radio>
                                <el-radio :label="false">No thank you</el-radio>
                            </el-radio-group>
                        </el-form-item>

                        <!-- Question 18 -->
                        <el-form-item label="18. How did you hear about Science Gallery's excursions?">
                            <el-input v-model="form.discoverySource" placeholder="Enter your answer"></el-input>
                        </el-form-item>

                        <!-- Question 19 -->
                        <el-form-item
                            label="19. Please note that bookings can be amended or cancelled by emailing school-bookings@unimelb.edu.au up until 14 days before the excursion date, at which point you will be issued an invoice based on the registered number of students. After that date, cancellations and amendments will be charged."
                            prop="bookingTerms">
                            <el-checkbox-group v-model="form.bookingTerms">
                                <el-checkbox :label="true">I accept</el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>


                    </el-form>
                    <el-button class="buttom" type="primary" @click="goToTargetPage"> Last Page</el-button>
                    <el-button class="buttom" type="primary" plain @click="submitForm"> Submit</el-button>

                </el-main>
            </el-container>

        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

// 数据属性
const form = ref({
    selectedProgram: '',
    datePreference2: '',
    studentCount: '',
    studentLevels: [],
    learningArea: '',
    isLowSES: null,
    abnNumber: '',
    specificNeeds: '',
    additionalInfo: '',
    mailingListSignup: null,
    discoverySource: '',
    bookingTerms: [],
    // 其他字段...
});

// 表单验证规则
const rules = {
    selectedProgram: [{ required: true, message: "Please select a program", trigger: "change" }],
    datePreference2: [{ required: true, message: 'Preference Data is required', trigger: 'blur' }],
    studentCount: [
        { required: true, message: 'The student count is required', trigger: 'blur' },
        { type: 'number', min: 20, max: 50, message: 'Student count must be between 20 and 50', trigger: ['blur', 'change'] }
    ],
    studentLevels: [
        { required: true, message: 'Please select at least one student level', trigger: 'change' }
    ],
    learningArea: [
        { required: true, message: 'Please enter the learning area or subject', trigger: 'blur' }
    ],
    isLowSES: [{ required: true, message: 'Please select an option', trigger: 'change' }],
    abnNumber: [{ required: true, message: 'ABN number is required', trigger: 'blur' }],
    specificNeeds: [{ required: true, message: 'Please describe any specific needs', trigger: 'blur' }],
    additionalInfo: [{ required: false }], // This field is not marked as required
    mailingListSignup: [
        { required: true, message: 'Please select an option', trigger: 'change' }
    ],
    bookingTerms: [
        { type: 'array', required: true, message: 'You must accept the terms', trigger: 'change' }
    ],
    // 其他验证规则...
};
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
    router.push({ name: 'teacherpage' }); // 确保你的路由配置中有对应的路由
}


const validateAndGoToTargetPage = () => {
    formRef.value.validate((valid) => {
        if (valid) {
            goToTargetPage();
        } else {
            console.log('error submit!!');
            // 这里可以处理错误情况，比如显示一个错误消息
        }
    });
};


import { ElMessageBox, ElMessage } from 'element-plus';

const submitForm = () => {
    formRef.value.validate((valid) => {
        if (valid) {
            // 表单验证成功后，弹出确认对话框
            open();
        } else {
            // 验证失败时的处理
            ElMessage({
                type: 'error',
                message: 'Please correct the errors in the form.',
            });
        }
    });
};

const open = () => {
    ElMessageBox.confirm(
        'Do you want to submit the form?',
        'Confirm Submission',
        {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'warning',
        }
    ).then(() => {
        // 点击确认后的处理，比如提交表单
        ElMessage({
            type: 'success',
            message: 'Submission Success',
        });
        // 这里可以放置表单提交到服务器的代码
    }).catch(() => {
        // 如果点击取消或关闭对话框，可以不做处理或显示消息
        ElMessage({
            type: 'info',
            message: 'Submission Canceled',
        });
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
    height: auto;
    margin-left: 1000px;
    margin-top: 3%;

}

.el-container {
    margin-top: -150px
}

.form-section {
    padding: 20px;
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, .1);
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
    color: black;
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
    display: flex;
    flex-direction: row;
    margin-bottom: 10px;

}
</style>