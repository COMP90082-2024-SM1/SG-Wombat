<template>

    <el-table ref="tableRef" row-key="date" :data="filteredData" style="width: 100%">
        <el-table-column prop="date" label="Date" sortable width="120" column-key="date" :filters="[
            { text: '2016-05-01', value: '2016-05-01' },
            { text: '2016-05-02', value: '2016-05-02' },
            { text: '2016-05-03', value: '2016-05-03' },
            { text: '2016-05-04', value: '2016-05-04' },
        ]" :filter-method="filterHandler" />
        <el-table-column prop="name" label="Name" width="120" />
        <el-table-column prop="address" label="Address" :formatter="formatter" />


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
  <el-button @click="resetDateFilter">Reset Date</el-button>
  <el-button @click="clearFilter">Reset All</el-button>



  <!-- Program Details Dialog -->
  <el-dialog v-model="detailsDialogVisible" title="Booking Details">
    <el-form :model="selectedProgram" label-width="120px">
      <!-- Form fields here -->
      <el-form-item label="Program Name">
        <el-input v-model="selectedProgram.name" disabled />
      </el-form-item>

      <el-form-item label="Max People">
        <el-input v-model="selectedProgram.people" disabled />
      </el-form-item>

      <!-- Add other fields here -->

    </el-form>
  </el-dialog>


  <!-- Edit Program Dialog -->
  <el-dialog v-model="editDialogVisible" title="Edit Program">
    <el-form :model="editedProgram" label-width="auto">
      <!-- Form fields here -->
      <el-form-item label="Program Name">
        <el-input v-model="editedProgram.name" />
      </el-form-item>

      <el-form-item label="Max People">
        <el-input v-model="editedProgram.people" />
      </el-form-item>

      <!-- Add other fields here -->

      <el-form-item>
        <el-button type="primary" @click="saveChanges">Save Changes</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <!-- for view details by clicking on view details button -->
  <el-dialog v-model="dialogDescVisible" title="Booking Details" width="80%" @closed="resetCurrentStep">
    <el-row gutter='20'>
      <!-- 左侧栏，保留现有内容 -->
      <el-col :span="15">
        <!-- steps to divide into different parts -->
        <el-steps :active="currentStep" align-center process-status="finish" finish-status="process">
          <el-step title="Delivery" :icon="Document" @click="currentStep = 0" />
          <el-step title="Cohort" :icon="School" @click="currentStep = 1" />
          <el-step title="Contact" :icon="User" @click="currentStep = 2" />
          <!-- <el-step title="Actions" :icon="Clock" @click="currentStep = 3" /> -->
          <el-step title="Bus" :icon="Van" @click="currentStep = 3" />
          <el-step title="Invoice" :icon="Memo" @click="currentStep = 4" />
          <!-- <el-step title="Budget" :icon="Money" @click="currentStep = 6" /> -->
        </el-steps>
        <br />
        <!-- step 0 Delivery -->
        <div v-show="currentStep === 0">
          <el-descriptions :column="2" size="large" border>
            <el-descriptions-item label="Program Stream">{{ bookingDetails.programStream }}</el-descriptions-item>
            <el-descriptions-item label="Request Confirmed?">{{ bookingDetails.requestConfirmed
              }}</el-descriptions-item>
            <el-descriptions-item label="Status">{{ bookingDetails.status }}</el-descriptions-item>
            <el-descriptions-item label="Facilitators">{{ bookingDetails.facilitators }}</el-descriptions-item>
            <el-descriptions-item label="Delivery Location" span="2">{{ bookingDetails.deliveryLocation
              }}</el-descriptions-item>
            <el-descriptions-item label="School" span="2">{{ bookingDetails.school }}</el-descriptions-item>
            <el-descriptions-item label="1st Date Preference">{{ bookingDetails.prefDate1 }}</el-descriptions-item>
            <el-descriptions-item label="2nd Date Preference">{{ bookingDetails.prefDate2 }}</el-descriptions-item>
            <el-descriptions-item label="Program Date">{{ bookingDetails.programDate }}</el-descriptions-item>
            <el-descriptions-item label="Start Time">{{ bookingDetails.startTime }}</el-descriptions-item>
            <el-descriptions-item label="End Time">{{ bookingDetails.endTime }}</el-descriptions-item>
            <el-descriptions-item label="Run Time">{{ bookingDetails.runTime }}</el-descriptions-item>
            <el-descriptions-item label="Reporting>3hrs">{{ bookingDetails.reporting3hrs }}</el-descriptions-item>
            <el-descriptions-item label="Program Category">{{ bookingDetails.programCat }}</el-descriptions-item>
            <el-descriptions-item label="Modules" span="2">{{ bookingDetails.modules }}</el-descriptions-item>
            <el-descriptions-item label="Exhibition">{{ bookingDetails.exhibition }}</el-descriptions-item>
            <el-descriptions-item label="Bus Required?">{{ bookingDetails.busRequired
              }}</el-descriptions-item>
            <el-descriptions-item label="Bus Booked">{{ bookingDetails.busBooked }}</el-descriptions-item>
            <el-descriptions-item label="To-do list">{{ bookingDetails.todoListType }}</el-descriptions-item>
            <el-descriptions-item label="Notes" span="2">{{ bookingDetails.notes }}</el-descriptions-item>
          </el-descriptions>

        </div>

        <!-- step 1 Cohort -->
        <div v-show="currentStep === 1">
          <el-descriptions :column="2" size="large" border>
            <el-descriptions-item label="School" span="2">{{ bookingDetails.school }}</el-descriptions-item>
            <el-descriptions-item label="Partner School?">{{ bookingDetails.partnerSchool }}</el-descriptions-item>
            <el-descriptions-item label="Student Year">{{ bookingDetails.studentYear }}</el-descriptions-item>
            <el-descriptions-item label="Student # (Registered)">{{ bookingDetails.regStudentsNo
              }}</el-descriptions-item>
            <el-descriptions-item label="Student # (Attended)">{{ bookingDetails.attendedStudentsNo
              }}</el-descriptions-item>
            <el-descriptions-item label="Low SES" span="2">{{ bookingDetails.lowSes }}</el-descriptions-item>
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
            <el-descriptions-item label="Email Address">{{ bookingDetails.emailAddress }}</el-descriptions-item>
            <el-descriptions-item label="Phone Number">{{ bookingDetails.phoneNumber }}</el-descriptions-item>
            <el-descriptions-item label="Teaching Area">{{ bookingDetails.teachingArea }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- step 3 Bus -->
        <div v-show="currentStep === 3">
          <el-descriptions :column="2" size="large" border>
            <el-descriptions-item label="Process Status">{{ bookingDetails.processStatus }}</el-descriptions-item>
            <el-descriptions-item label="Days Remaining">{{ bookingDetails.daysRemaining }}</el-descriptions-item>
            <el-descriptions-item label="Bus Status">{{ bookingDetails.busStatus }}</el-descriptions-item>
            <el-descriptions-item label="Quote #">{{ bookingDetails.quote }}</el-descriptions-item>
            <el-descriptions-item label="Price w/o GST">{{ bookingDetails.priceWoGST }}</el-descriptions-item>
            <el-descriptions-item label="Price in Full">{{ bookingDetails.priceFull }}</el-descriptions-item>
            <el-descriptions-item label="Credit Surcharge">{{ bookingDetails.creditSurcharge
              }}</el-descriptions-item>
            <el-descriptions-item label="Date Paid">{{ bookingDetails.datePaid }}</el-descriptions-item>
            <el-descriptions-item label="Card Owner">{{ bookingDetails.cardOwner }}</el-descriptions-item>
            <el-descriptions-item label="Bus Invoice #">{{ bookingDetails.busInvoiceNo }}</el-descriptions-item>
            <el-descriptions-item label="Saved Receipt">{{ bookingDetails.savedReceipt }}</el-descriptions-item>
            <el-descriptions-item label="Enter into Expense Master">{{ bookingDetails.expenseMaster
              }}</el-descriptions-item>
            <el-descriptions-item label="Pin/Categorise Email">{{ bookingDetails.pinEmail }}</el-descriptions-item>
            <el-descriptions-item label="Times in MSC">{{ bookingDetails.timesInMSC }}</el-descriptions-item>
            <el-descriptions-item label="Bus Notes">{{ bookingDetails.busNotes }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- step 4 Invoice -->
        <div v-show="currentStep === 4">
          <el-descriptions :column="2" size="large" border>
            <el-descriptions-item label="Amount">{{ bookingDetails.amount }}</el-descriptions-item>
            <el-descriptions-item label="ABN">{{ bookingDetails.abn }}</el-descriptions-item>
            <el-descriptions-item label="Invoice #" span="2">{{ bookingDetails.invoiceNo }}</el-descriptions-item>
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
import { ref, computed, reactive } from 'vue';
import type { TableColumnCtx, TableInstance } from 'element-plus'
import { Document, School, Van, User, Memo } from '@element-plus/icons-vue'

interface User {
  date: string
  name: string
  address: string
  tag: string
}

const search = ref('');

const tableRef = ref<TableInstance>()

const resetDateFilter = () => {
  tableRef.value!.clearFilter(['date'])
}

const filterData = (tag: string) => {
  if (tableRef.value) {
    // 正确调用 filter 方法，明确指定要筛选的列和值
    tableRef.value.filter('tag', tag);
  }
}



// TODO: improvement typing when refactor table
const clearFilter = () => {
  // eslint-disable-next-line @typescript-eslint/ban-ts-comment
  // @ts-expect-error
  tableRef.value!.clearFilter()
}
const formatter = (row: User, column: TableColumnCtx<User>) => {
  return row.address
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


const filteredData = computed(() => {
    return tableData.filter((item) => {
        return item.date.includes(search.value) ||
               item.name.toLowerCase().includes(search.value.toLowerCase()) ||
               item.address.toLowerCase().includes(search.value.toLowerCase()) ||
               item.tag.toLowerCase().includes(search.value.toLowerCase());
    });
});


const tableData: User[] = reactive([
    {
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Confirmed',
    },
    {
        date: '2016-05-02',
        name: 'Jerry',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Other',
    },
    {
        date: '2016-05-04',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Confirmed',
    },
    {
        date: '2016-05-01',
        name: 'Jerry',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Other',
    },
]);


const selectedProgram = ref<User | null>(null);

// handleRowClick: to be deleted later if not use row click
const editedProgram = ref<User | null>(null);
const editDialogVisible = ref(false);

// Checklist 状态存储
const checklistStates = reactive(new Map<string, string[]>());

const saveTodoList = () => {
  if (selectedProgram.value) {
    checklistStates.set(selectedProgram.value.date, todoList.value);
    console.log('Saved ToDo List for', selectedProgram.value.date, ':', todoList.value);
    closeDialog();  // Optionally close dialog after save
  }
};



const showDetails = (program: User) => {
  selectedProgram.value = program;
  dialogDescVisible.value = true;

  if (!checklistStates.has(program.date)) {
    checklistStates.set(program.date, []);
  }
  todoList.value = checklistStates.get(program.date) || [];
};


const showEditForm = (program: User) => {
  editedProgram.value = { ...program }; // Clone the program object
  editDialogVisible.value = true;
};


//// for booking details
const currentStep = ref(0)
const resetCurrentStep = () => {
  currentStep.value = 0
}
// const ref storing program details // to be updated later to take data from api
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



const dialogDescVisible = ref(false);
const todoList = ref([]);

const closeDialog = () => {
  dialogDescVisible.value = false;
  if (selectedProgram.value) {
    todoList.value = checklistStates.get(selectedProgram.value.date) || [];
  }
};




</script>

<style scoped>
/* ToDo List 样式调整 */
.todo-list {
  padding: 20px;
  background-color: #2E4DD4; /* 深蓝背景 */
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
  /* Element UI 蓝色 */
  border-color: #409EFF;
}

.el-button {
  margin-top: 20px;
}

/* el steps style */
.el-step {
  cursor: pointer;
}
</style>
