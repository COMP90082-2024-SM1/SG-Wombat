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
                    <el-select v-model="form.programStream" filterable clearable placeholder="Select Program Stream"
                        :default-first-option="true">
                        <el-option v-for="option in programStreamOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="Request Confirmed?">
                    <el-radio-group v-model="form.requestConfirmed">
                        <el-radio label="confirmed">Confirmed</el-radio>
                        <el-radio label="unconfirmed">Unconfirmed</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Status">
                    <el-select v-model="form.status" filterable placeholder="Select Status"
                        :default-first-option="true">
                        <el-option v-for="option in statusOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="Facilitators">
                    <el-input v-model="form.facilitators"></el-input>
                </el-form-item>
                <el-form-item label="Delivery Location">
                    <el-select v-model="form.deliveryLocation" multiple filterable
                        placeholder="Select Delivery Location" :default-first-option="true">
                        <el-option v-for="option in deliveryLocationOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
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
                    <el-time-select v-model="form.startTime" placeholder="Start Time" start="08:30" step="00:05"
                        end="18:30"></el-time-select>
                </el-form-item>

                <el-form-item label="End Time">
                    <el-time-select v-model="form.endTime" placeholder="End Time" start="08:30" step="00:05"
                        end="18:30"></el-time-select>
                </el-form-item>
                <!-- read only computed field-->
                <el-form-item label="Run Time">
                    <el-input v-model="form.runTime" readonly></el-input>
                </el-form-item>
                <el-form-item label="Reporting>3hrs">
                    <el-radio-group v-model="form.reporting3hrs">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Program Category">
                    <el-input v-model="form.programCat"></el-input>
                </el-form-item>
                <el-form-item label="Modules">
                    <el-select v-model="form.modules" multiple filterable placeholder="Select Modules"
                        :default-first-option="true">
                        <el-option v-for="option in moduleOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Exhibition">
                    <el-select v-model="form.exhibition" filterable allow-create placeholder="Select Exhibition"
                        :default-first-option="true">
                        <el-option v-for="option in exhibitionOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Bus Required">
                    <el-radio-group v-model="form.busRequired">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="Bus Booked">
                    <el-radio-group v-model="form.busBooked">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="To-Do List Type">
                    <el-select v-model="form.todoListType" placeholder="Select To-Do List Type">
                        <el-option v-for="option in todoListTypeOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Notes">
                    <el-input type="textarea" v-model="form.notes"></el-input>
                </el-form-item>

            </div>

            <!-- step 1 Cohort -->
            <div v-show="currentStep === 1">
                <el-form-item label="Partner School">
                    <el-radio-group v-model="form.partnerSchool">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Student Year">
                    <el-checkbox-group v-model="form.studentYear">
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
                <el-form-item label="Registered Students Number">
                    <el-input v-model="form.regStudentsNo" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Attended Students Number">
                    <el-input v-model="form.attendedStudentsNo" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Low SES">
                    <el-radio-group v-model="form.lowSes">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                    </el-radio-group>
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
                    <el-radio-group v-model="form.processStatus">
                        <el-radio label="contact">Contact Teacher</el-radio>
                        <el-radio label="delivered">Delivered</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Days Remaining">
                    <el-input v-model="daysRemaining" readonly></el-input>
                </el-form-item>
                <el-form-item label="Bus Status">
                    <el-select v-model="form.busStatus" placeholder="Select Bus Status">
                        <el-option v-for="option in busStatusOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
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
                    <el-radio-group v-model="form.savedReceipt">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Expense Master">
                    <el-radio-group v-model="form.expenseMaster">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="PIN Email">
                    <el-radio-group v-model="form.pinEmail">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Times in MSC">
                    <el-radio-group v-model="form.timesInMSC">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
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
import { ref, reactive, computed, watch } from 'vue';
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
    deliveryLocation: ['SGM: SGMT'],
    school: 'Aireys Inlet Primary School',
    prefDate1: '2024-06-18',
    prefDate2: '2024-06-20',
    programDate: '2024-06-18',
    startTime: '09:00',
    endTime: '',
    runTime: '',
    reporting3hrs: '',
    programCat: 'Other Workshops',
    modules: ['W: Future Food', 'W: Sustainable Communities'],
    exhibition: 'Non-Exhbition Linked',
    busRequired: 'N',
    busBooked: 'NA',
    todoListType: 'Todo List Template1',
    notes: 'TBC whether SCoE paying for buses',
    // cohort
    partnerSchool: 'Y',
    studentYear: ['11', '12'],
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
    // no need to send it to database?
    // daysRemaining: '',
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


const onSubmit = () => {
    console.log('Submitted:', form);
    visible.value = false;  // Close the dialog
};

const onCancel = () => {
    visible.value = false;
    console.log('Cancelled');
};

const programStreamOptions = ref([
    { label: 'SCoE: Excursions', value: 'SCoE: Excursions' },
    { label: 'Another Program Stream', value: 'Another Program Stream' },
    { label: 'Program Stream 3', value: 'Program Stream 3' },
    //
])

const statusOptions = ref([
    { label: 'Booking Request', value: 'Request' },
    { label: 'Processing', value: 'Processing' },
    { label: 'Delivered', value: 'Delivered' },
    { label: 'Postponed', value: 'Postponed' },
    { label: 'Cancelled', value: 'Cancelled' },
    { label: 'Upcoming', value: 'Upcoming' },
    //
])

const deliveryLocationOptions = ref([
    { label: 'SGM: SGMT', value: 'SGM: SGMT' },
    { label: 'Location 2', value: 'Location 2' },
    { label: 'Location 3', value: 'Location 3' },
    // 
])

// todo: modules should come from program list (active + upcoming programs)
const moduleOptions = ref([
    { label: 'W: Future Food', value: 'Module 1' },
    { label: 'W: Sustainable Communities', value: 'Module 2' },
    { label: 'Module 3', value: 'Module 3' },
])

const exhibitionOptions = ref([
    { label: 'Non-Exhbition Linked', value: 'Non-Exhbition Linked' },
    { label: 'Not Natural', value: 'Not Natural' },
    { label: 'Ancient Lives', value: 'Ancient Lives' },

])

const todoListTypeOptions = ref([
    { label: 'Todo List Template1', value: 'Todo List Template1' },
    { label: 'Todo List Template2', value: 'Todo List Template2' },
    { label: 'Todo List Template3', value: 'Todo List Template3' },
])

const busStatusOptions = ref([
    { label: 'Requested Quote', value: 'Quote' },
    { label: 'Processing', value: 'Processing' },
    { label: 'Paid', value: 'Paid' },
    { label: 'Cancelled', value: 'Cancelled' }

])


const parseTime = (time) => {
    const [hours, minutes] = time.split(':').map(Number)
    return hours * 3600 + minutes * 60
}

const formatTime = (seconds) => {
    const h = Math.floor(seconds / 3600).toString().padStart(2, '0')
    const m = Math.floor((seconds % 3600) / 60).toString().padStart(2, '0')
    return `${h}:${m}`
}

// compute run time, is it really needed?
const runTime = computed(() => {
    if (form.value.startTime && form.value.endTime) {
        const start = parseTime(form.value.startTime)
        const end = parseTime(form.value.endTime)
        const diff = end - start
        if (diff >= 0) {
            return formatTime(diff)
        } else {
            return '00:00'
        }
    }
    return '00:00'
})

// watch for changes in run time and update form value
watch(runTime, (newVal) => {
    form.value.runTime = newVal
})

// compute days remaining
const daysRemaining = computed(() => {
    if (!form.value.programDate) {
        return 0
    }

    const programDate = new Date(form.value.programDate)
    const today = new Date()

    programDate.setHours(0, 0, 0, 0)
    today.setHours(0, 0, 0, 0)

    const diffTime = programDate.getTime() - today.getTime()
    const diffDays = Math.round(diffTime / (1000 * 60 * 60 * 24))

    return diffDays
})

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