<template>
    <el-table ref="tableRef" row-key="bookingID" :data="paginatedData" style="width: 100%">
        <el-table-column prop="bookingID" label="Booking ID" sortable width="120" column-key="bookingID" />
        <el-table-column prop="programStream" label="Program Stream" width="120" />
        <el-table-column prop="requestTime" label="Request Time" width="120" />
        <el-table-column prop="programDate" label="Program Date" width="120" />
        <el-table-column prop="startTime" label="Start Time" width="120" />
        <el-table-column prop="school" label="School" width="120" />

        <el-table-column prop="tag" label="All Bookings" width="150" :filters="[
        { text: 'Confirmed Bookings', value: 'Confirmed' },
        { text: 'Other Bookings', value: 'Other' },
    ]" :filter-method="filterTag" filter-placement="bottom-end">
            <template #default="scope">
                <el-tag :type="scope.row.tag === 'Other' ? '' : 'success'" disable-transitions>{{ scope.row.tag
                    }}</el-tag>
            </template>
        </el-table-column>
        <el-table-column align="right">
            <template #header>
                <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
                <el-button size="small" @click="showDetails(scope.row)">
                    Show Details
                </el-button>
                <el-button size="small" @click="showEditForm(scope.row)">
                    Edit
                </el-button>
                <el-button size="small" type="danger" @click="deleteProgram(scope.row)">
                    Delete
                </el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
        :page-sizes="[10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
        :total="filteredData.length">
    </el-pagination>

    <el-button @click="resetDateFilter">Reset Date</el-button>
    <el-button @click="clearFilter">Reset All</el-button>

    <!-- Program Details Dialog -->
    <el-dialog v-model="detailsDialogVisible" title="Booking Details">
        <el-form :model="selectedProgram" label-width="120px">
            <el-form-item label="Program Name">
                <el-input v-model="selectedProgram.name" disabled />
            </el-form-item>
            <el-form-item label="Max People">
                <el-input v-model="selectedProgram.people" disabled />
            </el-form-item>
        </el-form>
    </el-dialog>

    <!-- Edit Program Dialog -->
    <el-dialog v-model="editDialogVisible" title="Edit Program">
        <el-form :model="editedProgram" label-width="auto">
            <el-form-item label="Program Name">
                <el-input v-model="editedProgram.name" />
            </el-form-item>
            <el-form-item label="Max People">
                <el-input v-model="editedProgram.people" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="saveChanges">Save Changes</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>

    <!-- for view details by clicking on view details button -->
    <el-dialog v-model="dialogDescVisible" title="Booking Details" width="80%" @closed="resetCurrentStep">
        <el-row gutter="20">
            <!-- 左侧栏，保留现有内容 -->
            <el-col :span="15">
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
                    <el-descriptions :column="2" size="large" border>
                        <el-descriptions-item label="Program Stream">{{ bookingDetails.programStream
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Request Confirmed?">{{ bookingDetails.requestConfirmed
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Status">{{ bookingDetails.status }}</el-descriptions-item>
                        <el-descriptions-item label="Facilitators">{{ bookingDetails.facilitators
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Delivery Location" span="2">{{ bookingDetails.deliveryLocation
                            }}</el-descriptions-item>
                        <el-descriptions-item label="School" span="2">{{ bookingDetails.school }}</el-descriptions-item>
                        <el-descriptions-item label="1st Date Preference">{{ bookingDetails.prefDate1
                            }}</el-descriptions-item>
                        <el-descriptions-item label="2nd Date Preference">{{ bookingDetails.prefDate2
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Program Date">{{ bookingDetails.programDate
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Start Time">{{ bookingDetails.startTime }}</el-descriptions-item>
                        <el-descriptions-item label="End Time">{{ bookingDetails.endTime }}</el-descriptions-item>
                        <el-descriptions-item label="Run Time">{{ bookingDetails.runTime }}</el-descriptions-item>
                        <el-descriptions-item label="Reporting>3hrs">{{ bookingDetails.reporting3hrs
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Program Category">{{ bookingDetails.programCat
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Modules" span="2">{{ bookingDetails.modules
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Exhibition">{{ bookingDetails.exhibition }}</el-descriptions-item>
                        <el-descriptions-item label="Bus Required?">{{ bookingDetails.busRequired
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Bus Booked">{{ bookingDetails.busBooked }}</el-descriptions-item>
                        <el-descriptions-item label="To-do list">{{ bookingDetails.todoListType
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Notes" span="2">{{ bookingDetails.notes }}</el-descriptions-item>
                    </el-descriptions>
                </div>

                <!-- step 1 Cohort -->
                <div v-show="currentStep === 1">
                    <el-descriptions :column="2" size="large" border>
                        <el-descriptions-item label="School" span="2">{{ bookingDetails.school }}</el-descriptions-item>
                        <el-descriptions-item label="Partner School?">{{ bookingDetails.partnerSchool
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Student Year">{{ bookingDetails.studentYear
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Student # (Registered)">{{ bookingDetails.regStudentsNo
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Student # (Attended)">{{ bookingDetails.attendedStudentsNo
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Low SES" span="2">{{ bookingDetails.lowSes
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Accessibility Needs" span="2">{{ bookingDetails.accNeeds
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Allergens & Anaphylaxis" span="2">{{ bookingDetails.allergyNeeds
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Teacher’s Notes" span="2">{{ bookingDetails.teacherNotes
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Comments">{{ bookingDetails.commentsSG }}</el-descriptions-item>
                    </el-descriptions>
                </div>

                <!-- step 2 Contact -->
                <div v-show="currentStep === 2">
                    <el-descriptions :column="1" size="large" border>
                        <el-descriptions-item label="First Name">{{ bookingDetails.firstName }}</el-descriptions-item>
                        <el-descriptions-item label="Last Name">{{ bookingDetails.lastName }}</el-descriptions-item>
                        <el-descriptions-item label="Email Address">{{ bookingDetails.emailAddress
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Phone Number">{{ bookingDetails.phoneNumber
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Teaching Area">{{ bookingDetails.teachingArea
                            }}</el-descriptions-item>
                    </el-descriptions>
                </div>

                <!-- step 3 Bus -->
                <div v-show="currentStep === 3">
                    <el-descriptions :column="2" size="large" border>
                        <el-descriptions-item label="Process Status">{{ bookingDetails.processStatus
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Days Remaining">{{ bookingDetails.daysRemaining
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Bus Status">{{ bookingDetails.busStatus }}</el-descriptions-item>
                        <el-descriptions-item label="Quote #">{{ bookingDetails.quote }}</el-descriptions-item>
                        <el-descriptions-item label="Price w/o GST">{{ bookingDetails.priceWoGST
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Price in Full">{{ bookingDetails.priceFull
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Credit Surcharge">{{ bookingDetails.creditSurcharge
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Date Paid">{{ bookingDetails.datePaid }}</el-descriptions-item>
                        <el-descriptions-item label="Card Owner">{{ bookingDetails.cardOwner }}</el-descriptions-item>
                        <el-descriptions-item label="Bus Invoice #">{{ bookingDetails.busInvoiceNo
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Saved Receipt">{{ bookingDetails.savedReceipt
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Enter into Expense Master">{{ bookingDetails.expenseMaster
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Pin/Categorise Email">{{ bookingDetails.pinEmail
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Times in MSC">{{ bookingDetails.timesInMSC
                            }}</el-descriptions-item>
                        <el-descriptions-item label="Bus Notes">{{ bookingDetails.busNotes }}</el-descriptions-item>
                    </el-descriptions>
                </div>

                <!-- step 4 Invoice -->
                <div v-show="currentStep === 4">
                    <el-descriptions :column="2" size="large" border>
                        <el-descriptions-item label="Amount">{{ bookingDetails.amount }}</el-descriptions-item>
                        <el-descriptions-item label="ABN">{{ bookingDetails.abn }}</el-descriptions-item>
                        <el-descriptions-item label="Invoice #" span="2">{{ bookingDetails.invoiceNo
                            }}</el-descriptions-item>
                    </el-descriptions>
                </div>
            </el-col>
            <!-- 右侧栏，新的 Checklist 内容 -->
            <el-col :span="9">
                <div class="todo-list">
                    <div class="todo-header">TO DO LIST</div>
                    <el-checkbox-group v-model="todoList">
                        <el-checkbox label="Item 1" name="type">Item 1</el-checkbox>
                        <el-checkbox label="Item 2" name="type">Item 2</el-checkbox>
                        <el-checkbox label="Item 3" name="type">Item 3</el-checkbox>
                        <!-- 更多 checkbox -->
                    </el-checkbox-group>
                </div>
            </el-col>
        </el-row>
        <template #footer>
            <el-button @click="closeDialog">Back</el-button>
            <el-button type="primary" @click="saveTodoList">Save</el-button>
        </template>
    </el-dialog>
</template>

<script lang="ts" setup>
import { ref, computed, reactive, watch } from 'vue';
import type { TableColumnCtx, TableInstance } from 'element-plus'
import { Document, School, Van, User, Memo } from '@element-plus/icons-vue'

interface User {
    bookingID: string
    programStream: string
    requestTime: string
    programDate: string
    startTime: string
    school: string
    tag: string
}

const search = ref('');

const tableRef = ref<TableInstance>()

const resetDateFilter = () => {
    tableRef.value!.clearFilter(['date'])
}

const filterData = (tag: string) => {
    if (tableRef.value) {
        tableRef.value.filter('tag', tag);
    }
}

const clearFilter = () => {
    tableRef.value!.clearFilter()
}

const filterTag = (value: string, row: User) => {
    return row.tag === value
}

const filterHandler = (
    value: string,
    row: User,
    column: TableColumnCtx<User>
) => {
    const property = column['property']
    return row[property] === value
}

const tableData: User[] = reactive([
    {
        bookingID: 'B001',
        programStream: 'Science',
        requestTime: '10:00 AM',
        programDate: '2024-06-01',
        startTime: '10:30 AM',
        school: 'School A',
        tag: 'Confirmed',
    },
    {
        bookingID: 'B002',
        programStream: 'Math',
        requestTime: '11:00 AM',
        programDate: '2024-06-02',
        startTime: '11:30 AM',
        school: 'School B',
        tag: 'Other',
    },
    {
        bookingID: 'B003',
        programStream: 'History',
        requestTime: '12:00 PM',
        programDate: '2024-06-03',
        startTime: '12:30 PM',
        school: 'School C',
        tag: 'Confirmed',
    },
    {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    },
    {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    },
    {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    },
    {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    },
    {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    },
    {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    },
    {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    },
    {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    },
    {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    }, {
        bookingID: 'B004',
        programStream: 'Geography',
        requestTime: '01:00 PM',
        programDate: '2024-06-04',
        startTime: '01:30 PM',
        school: 'School D',
        tag: 'Other',
    },
    {
        bookingID: 'B001',
        programStream: 'Science',
        requestTime: '10:00 AM',
        programDate: '2024-06-01',
        startTime: '10:30 AM',
        school: 'School A',
        tag: 'Confirmed',
    },
]);

const currentPage = ref(1);
const pageSize = ref(10);

const filteredData = computed(() => {
    return tableData.filter((item) => {
        return item.bookingID.includes(search.value) ||
            item.programStream.toLowerCase().includes(search.value.toLowerCase()) ||
            item.requestTime.toLowerCase().includes(search.value.toLowerCase()) ||
            item.programDate.toLowerCase().includes(search.value.toLowerCase()) ||
            item.startTime.toLowerCase().includes(search.value.toLowerCase()) ||
            item.school.toLowerCase().includes(search.value.toLowerCase()) ||
            item.tag.toLowerCase().includes(search.value.toLowerCase());
    });
});

const paginatedData = computed(() => {
    const start = (currentPage.value - 1) * pageSize.value;
    const end = start + pageSize.value;
    return filteredData.value.slice(start, end);
});

watch([currentPage, pageSize, filteredData], () => {
    console.log('Current Page:', currentPage.value);
    console.log('Page Size:', pageSize.value);
    console.log('Filtered Data Length:', filteredData.value.length);
    console.log('Paginated Data:', paginatedData.value);
});

const handleSizeChange = (newSize) => {
    pageSize.value = newSize;
    currentPage.value = 1;  // 当更改每页数量时，返回第一页
};

const handleCurrentChange = (newPage) => {
    currentPage.value = newPage;
};


const selectedProgram = ref<User | null>(null);

const editedProgram = ref<User | null>(null);
const editDialogVisible = ref(false);

const checklistStates = reactive(new Map<string, string[]>());

const saveTodoList = () => {
    if (selectedProgram.value) {
        checklistStates.set(selectedProgram.value.bookingID, todoList.value);
        console.log('Saved ToDo List for', selectedProgram.value.bookingID, ':', todoList.value);
        closeDialog();
    }
};

const showDetails = (program: User) => {
    selectedProgram.value = program;
    dialogDescVisible.value = true;

    if (!checklistStates.has(program.bookingID)) {
        checklistStates.set(program.bookingID, []);
    }
    todoList.value = checklistStates.get(program.bookingID) || [];
};

const showEditForm = (program: User) => {
    editedProgram.value = { ...program };
    editDialogVisible.value = true;
};

const currentStep = ref(0)
const resetCurrentStep = () => {
    currentStep.value = 0
}

const bookingDetails = ref({
    programStream: 'SCoE: Excursions',
    requestConfirmed: 'Confirmed',
    status: 'Processing',
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
    partnerSchool: 'Y',
    studentYear: '11,12',
    regStudentsNo: 50,
    attendedStudentsNo: 43,
    lowSes: 'N',
    accNeeds: 'NA',
    allergyNeeds: 'NA',
    teacherNotes: 'NA',
    commentsSG: '',
    firstName: 'Bob',
    lastName: 'Ross',
    emailAddress: 'bobross@gmail.com',
    phoneNumber: '0412345678',
    teachingArea: 'Humanities Leader, Geography & Psychology Teacher',
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
    amount: '',
    abn: '',
    invoiceNo: ''
});

const dialogDescVisible = ref(false);
const todoList = ref([]);

const closeDialog = () => {
    dialogDescVisible.value = false;
    if (selectedProgram.value) {
        todoList.value = checklistStates.get(selectedProgram.value.bookingID) || [];
    }
};
</script>

<style scoped>
.todo-list {
    padding: 20px;
    background-color: #2E4DD4;
    margin-left: 3%;
    margin-right: 3%;
    color: white;
    border-radius: 8px;
}

.todo-header {
    font-size: 20px;
    font-weight: bold;
    margin-bottom: 20px;
}

.el-checkbox {
    display: block;
    margin-bottom: 10px;
    border-color: white;
    color: white;
}

.el-checkbox__label {
    font-size: 16px;
}

.el-checkbox__input.is-checked .el-checkbox__inner,
.el-checkbox__input.is-indeterminate .el-checkbox__inner {
    background-color: #409EFF;
    border-color: #409EFF;
}

.el-button {
    margin-top: 20px;
}

.el-step {
    cursor: pointer;
}
</style>
