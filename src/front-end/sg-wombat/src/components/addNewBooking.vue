<template>
    <el-dialog v-model="visible" title="Add New Booking" @closed="resetCurrentStep">
        <!-- steps to divide into different parts -->
        <el-steps :active="currentStep" align-center process-status="finish" finish-status="process">
            <el-step title="Delivery" :icon="Document" @click="currentStep = 0" />
            <el-step title="Cohort" :icon="School" @click="currentStep = 1" />
            <el-step title="Contact" :icon="User" @click="currentStep = 2" />
            <el-step title="Bus" :icon="Van" @click="currentStep = 3" />
            <el-step title="Invoice" :icon="Memo" @click="currentStep = 4" />
        </el-steps>
        <br />

        <!-- step 0 Delivery -->
        <div v-show="currentStep === 0">
            <el-form :model="form" label-width="auto">
                <!-- Form fields here -->
                <el-form-item label="Program Name">
                    <el-input v-model="form.name" />
                </el-form-item>

                <el-form-item label="Maximum People">
                    <el-input-number v-model="form.maxPeople" :min="1" />
                </el-form-item>



                <el-form-item label="Tech Requirement">
                    <el-input v-model="form.techRequirement" />
                </el-form-item>

                <el-form-item label="Cost per Person">
                    <el-input-number v-model="form.costPerPerson" :min="0" />
                </el-form-item>

                <el-form-item label="Runtime">
                    <el-select v-model="form.runtime" placeholder="Select runtime">
                        <el-option label="1 hour" value="1 hour" />
                        <el-option label="2 hours" value="2 hours" />
                        <el-option label="3 hours" value="3 hours" />
                    </el-select>
                </el-form-item>

                <el-form-item label="Program Description">
                    <el-input type="textarea" v-model="form.description" />
                </el-form-item>


                <!-- Work Days checkbox group -->
                <el-form-item label="Available Days">
                    <el-checkbox-group v-model="form.workDays">
                        <el-checkbox label="Tuesday">Tuesday</el-checkbox>
                        <el-checkbox label="Wednesday">Wednesday</el-checkbox>
                        <el-checkbox label="Thursday">Thursday</el-checkbox>
                        <el-checkbox label="Friday">Friday</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>

                <el-form-item label="Status">
                    <el-radio-group v-model="form.programState">
                        <el-radio label="active">Active</el-radio>
                        <el-radio label="archived">Archived</el-radio>
                        <el-radio label="upcoming">Upcoming</el-radio>
                    </el-radio-group>
                </el-form-item>

                <!-- <el-form-item>
                    <el-button type="primary" @click="onSubmit">Create Program</el-button>
                    <el-button @click="onCancel">Cancel</el-button>
                </el-form-item> -->
            </el-form>
        </div>

        <div class="form-bttns">
            <el-button @click="currentStep--" v-show="currentStep > 0">Back</el-button>
            <el-button @click="currentStep++" v-show="currentStep < 4">Next</el-button>
            <el-button v-show="currentStep === 4" type="primary" @click="onSubmit">Create Booking</el-button>
        </div>


    </el-dialog>
</template>

<script setup>
import { ref, reactive, computed } from 'vue';
import { defineProps, defineEmits } from 'vue';
import { Document, School, Van, User, Memo } from '@element-plus/icons-vue'


const props = defineProps({
    formVisible: Boolean
});

const emits = defineEmits(['update:formVisible']);

const visible = computed({
    get: () => props.formVisible,
    set: (value) => emits('update:formVisible', value)
});

const currentStep = ref(0)
const resetCurrentStep = () => {
    currentStep.value = 0
}

const form = reactive({
    name: '',
    maxPeople: 1,
    techRequirement: '',
    costPerPerson: 0,
    runtime: '',
    description: '',
    status: 'active',
    workDays: []  // Initialize the workDays array for checkboxes
});

const onSubmit = () => {
    console.log('Submitted:', form);
    visible.value = false;  // Close the dialog
};

const onCancel = () => {
    visible.value = false;
    console.log('Cancelled');
};
</script>

<style scoped>
/* el steps style */
.el-step {
    cursor: pointer;
}

.form-bttns {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>