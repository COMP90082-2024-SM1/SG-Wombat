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

        <el-form :model="form" label-width="auto">
            <!-- step 0 Delivery -->
            <div v-show="currentStep === 0">
                <el-form-item label="Program Stream">
                    <el-select v-model="form.programStream" placeholder="Select Program Stream" filterable clearable>
                        <el-option label="SCoE: Excursions" value="SCoE: Excursions"></el-option>
                        <el-option label="Another Program Stream" value="Another Program Stream"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="Request Confirmed？">
                    <el-input v-model="form.requestConfirmed"></el-input>
                </el-form-item>
                <el-form-item label="Status">
                    <el-input v-model="form.status"></el-input>
                </el-form-item>
                <el-form-item label="Facilitators">
                    <el-input v-model="form.facilitators"></el-input>
                </el-form-item>
                <el-form-item label="Delivery Location">
                    <el-input v-model="form.deliveryLocation"></el-input>
                </el-form-item>
                <el-form-item label="School">
                    <el-input v-model="form.school"></el-input>
                </el-form-item>
                <el-form-item label="Preferred Date 1">
                    <el-date-picker v-model="form.prefDate1" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="Preferred Date 2">
                    <el-date-picker v-model="form.prefDate2" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="Program Date">
                    <el-date-picker v-model="form.programDate" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="Start Time">
                    <el-time-picker v-model="form.startTime" type="time"></el-time-picker>
                </el-form-item>
                <el-form-item label="End Time">
                    <el-time-picker v-model="form.endTime" type="time"></el-time-picker>
                </el-form-item>
                <el-form-item label="Run Time">
                    <el-input v-model="form.runTime"></el-input>
                </el-form-item>
                <el-form-item label="Reporting 3hrs">
                    <el-input v-model="form.reporting3hrs"></el-input>
                </el-form-item>
                <el-form-item label="Program Category">
                    <el-input v-model="form.programCat"></el-input>
                </el-form-item>
                <el-form-item label="Modules">
                    <el-input v-model="form.modules"></el-input>
                </el-form-item>
                <el-form-item label="Exhibition">
                    <el-input v-model="form.exhibition"></el-input>
                </el-form-item>
                <el-form-item label="Bus Required">
                    <el-input v-model="form.busRequired"></el-input>
                </el-form-item>
                <el-form-item label="Bus Booked">
                    <el-input v-model="form.busBooked"></el-input>
                </el-form-item>
                <el-form-item label="To-Do List Type">
                    <el-input v-model="form.todoListType"></el-input>
                </el-form-item>
                <el-form-item label="Notes">
                    <el-input type="textarea" v-model="form.notes"></el-input>
                </el-form-item>

            </div>

            <!-- step 1 Cohort -->
            <div v-show="currentStep === 1">
                <el-form-item label="Partner School">
                    <el-input v-model="form.partnerSchool"></el-input>
                </el-form-item>
                <el-form-item label="Student Year">
                    <el-input v-model="form.studentYear"></el-input>
                </el-form-item>
                <el-form-item label="Registered Students Number">
                    <el-input v-model="form.regStudentsNo" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Attended Students Number">
                    <el-input v-model="form.attendedStudentsNo" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Low SES">
                    <el-input v-model="form.lowSes"></el-input>
                </el-form-item>
                <el-form-item label="Accessibility Needs">
                    <el-input type="textarea" v-model="form.accNeeds"></el-input>
                </el-form-item>
                <el-form-item label="Allergy Needs">
                    <el-input type="textarea" v-model="form.allergyNeeds"></el-input>
                </el-form-item>
                <el-form-item label="Teacher Notes">
                    <el-input type="textarea" v-model="form.teacherNotes"></el-input>
                </el-form-item>
                <el-form-item label="Comments">
                    <el-input type="textarea" v-model="form.commentsSG"></el-input>
                </el-form-item>
            </div>

            <!-- step 2 Contact -->
            <div v-show="currentStep === 2">
                <el-form-item label="First Name">
                    <el-input v-model="form.firstName"></el-input>
                </el-form-item>
                <el-form-item label="Last Name">
                    <el-input v-model="form.lastName"></el-input>
                </el-form-item>
                <el-form-item label="Email Address">
                    <el-input v-model="form.emailAddress"></el-input>
                </el-form-item>
                <el-form-item label="Phone Number">
                    <el-input v-model="form.phoneNumber"></el-input>
                </el-form-item>
                <el-form-item label="Teaching Area">
                    <el-input v-model="form.teachingArea"></el-input>
                </el-form-item>
            </div>

            <!-- step 3 Bus -->
            <div v-show="currentStep === 3">
                <el-form-item label="Process Status">
                    <el-input v-model="form.processStatus"></el-input>
                </el-form-item>
                <el-form-item label="Days Remaining">
                    <el-input v-model="form.daysRemaining"></el-input>
                </el-form-item>
                <el-form-item label="Bus Status">
                    <el-input v-model="form.busStatus"></el-input>
                </el-form-item>
                <el-form-item label="Quote">
                    <el-input v-model="form.quote"></el-input>
                </el-form-item>
                <el-form-item label="Price Without GST">
                    <el-input v-model="form.priceWoGST"></el-input>
                </el-form-item>
                <el-form-item label="Price Full">
                    <el-input v-model="form.priceFull"></el-input>
                </el-form-item>
                <el-form-item label="Credit Surcharge">
                    <el-input v-model="form.creditSurcharge"></el-input>
                </el-form-item>
                <el-form-item label="Date Paid">
                    <el-date-picker v-model="form.datePaid" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="Card Owner">
                    <el-input v-model="form.cardOwner"></el-input>
                </el-form-item>
                <el-form-item label="Bus Invoice No">
                    <el-input v-model="form.busInvoiceNo"></el-input>
                </el-form-item>
                <el-form-item label="Saved Receipt">
                    <el-input v-model="form.savedReceipt"></el-input>
                </el-form-item>
                <el-form-item label="Expense Master">
                    <el-input v-model="form.expenseMaster"></el-input>
                </el-form-item>
                <el-form-item label="PIN Email">
                    <el-input v-model="form.pinEmail"></el-input>
                </el-form-item>
                <el-form-item label="Times in MSC">
                    <el-input v-model="form.timesInMSC"></el-input>
                </el-form-item>
                <el-form-item label="Bus Notes">
                    <el-input type="textarea" v-model="form.busNotes"></el-input>
                </el-form-item>
            </div>

            <!-- step 4 Invoice -->
            <div v-show="currentStep === 4">
                <el-form-item label="Amount">
                    <el-input v-model="form.amount"></el-input>
                </el-form-item>
                <el-form-item label="ABN">
                    <el-input v-model="form.abn"></el-input>
                </el-form-item>
                <el-form-item label="Invoice No">
                    <el-input v-model="form.invoiceNo"></el-input>
                </el-form-item>
            </div>

            <div class="form-bttns">
                <el-button @click="currentStep--" v-show="currentStep > 0">Back</el-button>
                <el-button @click="currentStep++" v-show="currentStep < 4">Next</el-button>
                <el-button v-show="currentStep === 4" type="primary" @click="onSubmit">Create Booking</el-button>
            </div>

        </el-form>



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

const form = ref({
    programStream: '',
    requestConfirmed: '',
    status: '',
    facilitators: 'Teacher Delivered',
    deliveryLocation: 'SGM: SGMT',
    school: 'Aireys Inlet Primary School',
    prefDate1: '2024-06-18',
    prefDate2: '2024-06-20',
    programDate: '2024-06-18',
    startTime: '',
    endTime: '',
    runTime: '',
    reporting3hrs: '',
    programCat: 'Other Workshops',
    modules: 'W: Future Food; W: Sustainable Communities',
    exhibition: 'Non-Exhbition Linked',
    busRequired: 'N',
    busBooked: 'NA',
    todoListType: 'Todo List Template1',
    notes: 'TBC whether SCoE paying for buses',
    // cohort
    partnerSchool: 'Y',
    studentYear: '11,12',
    regStudentsNo: 50,
    attendedStudentsNo: 43,
    lowSes: 'N',
    accNeeds: 'NA',
    allergyNeeds: 'NA',
    teacherNotes: 'NA',
    commentsSG: '',
    // contact
    firstName: 'Bob',
    lastName: 'Ross',
    emailAddress: 'bobross@gmail.com',
    phoneNumber: '0412345678',
    teachingArea: 'Humanities Leader, Geography & Psychology Teacher',
    // bus
    processStatus: '',
    daysRemaining: '',
    busStatus: '',
    quote: '',
    priceWoGST: '',
    priceFull: '',
    creditSurcharge: '',
    datePaid: '',
    cardOwner: '',
    busInvoiceNo: '',
    savedReceipt: '',
    expenseMaster: '',
    pinEmail: '',
    timesInMSC: '',
    busNotes: '',
    // invoice
    amount: '',
    abn: '',
    invoiceNo: ''

})



// const form = reactive({
//     name: '',
//     maxPeople: 1,
//     techRequirement: '',
//     costPerPerson: 0,
//     runtime: '',
//     description: '',
//     status: 'active',
//     workDays: []  // Initialize the workDays array for checkboxes
// });

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
.el-form {
    padding: 0 10px 0 10px;
}

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