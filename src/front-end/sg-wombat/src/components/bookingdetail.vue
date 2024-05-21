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
        <el-table-column align="right" fixed="right" width="260px">
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
    <!-- <el-dialog v-model="detailsDialogVisible" title="Booking Details">
        <el-form :model="selectedProgram" label-width="120px">
            <el-form-item label="Program Name">
                <el-input v-model="selectedProgram.name" disabled />
            </el-form-item>
            <el-form-item label="Max People">
                <el-input v-model="selectedProgram.people" disabled />
            </el-form-item>
        </el-form>
    </el-dialog> -->

    <!-- Edit Booking Dialog -->
    <el-dialog v-model="editDialogVisible" title="Edit Program" width="50%" @closed="resetCurrentStepEdit">
        <!-- steps to divide into different parts -->
        <el-steps :active="currentStepEdit" align-center process-status="finish" finish-status="process">
            <el-step title="Delivery" :icon="Document" @click="currentStepEdit = 0" />
            <el-step title="Cohort" :icon="School" @click="currentStepEdit = 1" />
            <el-step title="Contact" :icon="User" @click="currentStepEdit = 2" />
            <el-step title="Bus" :icon="Van" @click="currentStepEdit = 3" />
            <el-step title="Invoice" :icon="Memo" @click="currentStepEdit = 4" />
        </el-steps>
        <br />
        <el-form :model="form" label-width="auto">
            <!-- step 0 Delivery -->
            <div v-show="currentStepEdit === 0">
                <el-form-item label="Program Stream">
                    <el-select v-model="editedBooking.programStream" filterable clearable
                        placeholder="Select Program Stream" :default-first-option="true">
                        <el-option v-for="option in programStreamOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="Request Confirmed?">
                    <el-radio-group v-model="editedBooking.requestConfirmed">
                        <el-radio label="confirmed">Confirmed</el-radio>
                        <el-radio label="unconfirmed">Unconfirmed</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Status">
                    <el-select v-model="editedBooking.status" filterable placeholder="Select Status"
                        :default-first-option="true">
                        <el-option v-for="option in statusOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="Facilitators">
                    <el-input v-model="editedBooking.facilitators"></el-input>
                </el-form-item>
                <el-form-item label="Delivery Location">
                    <el-select v-model="editedBooking.deliveryLocation" multiple filterable
                        placeholder="Select Delivery Location" :default-first-option="true">
                        <el-option v-for="option in deliveryLocationOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="School">
                    <el-input v-model="editedBooking.school"></el-input>
                </el-form-item>
                <el-form-item label="Preferred Date 1">
                    <el-date-picker v-model="editedBooking.prefDate1" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="Preferred Date 2">
                    <el-date-picker v-model="editedBooking.prefDate2" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="Program Date">
                    <el-date-picker v-model="editedBooking.programDate" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="Start Time">
                    <el-time-select v-model="editedBooking.startTime" placeholder="Start Time" start="08:30"
                        step="00:05" end="18:30"></el-time-select>
                </el-form-item>

                <el-form-item label="End Time">
                    <el-time-select v-model="editedBooking.endTime" placeholder="End Time" start="08:30" step="00:05"
                        end="18:30"></el-time-select>
                </el-form-item>
                <!-- read only computed field-->
                <el-form-item label="Run Time">
                    <el-input v-model="editedBooking.runTime" readonly></el-input>
                </el-form-item>
                <el-form-item label="Reporting>3hrs">
                    <el-radio-group v-model="editedBooking.reporting3hrs">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Program Category">
                    <!-- todo: data should come from program list;-->
                    <el-select v-model="editedBooking.programCat" filterable placeholder="Select Program Category">
                        <el-option label="Schools only Tuesday" value="Tuesday" />
                        <el-option label="Other Workshops" value="Other" />
                    </el-select>
                </el-form-item>
                <el-form-item label="Modules">
                    <!-- todo: data should be filtered based on program category selection? not necessary though-->
                    <el-select v-model="editedBooking.modules" multiple filterable placeholder="Select Modules"
                        :default-first-option="true">
                        <el-option v-for="option in moduleOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Exhibition">
                    <el-select v-model="editedBooking.exhibition" filterable allow-create
                        placeholder="Select Exhibition" :default-first-option="true">
                        <el-option v-for="option in exhibitionOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Bus Required">
                    <el-radio-group v-model="editedBooking.busRequired">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="Bus Booked">
                    <el-radio-group v-model="editedBooking.busBooked">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="To-Do List Type">
                    <el-select v-model="editedBooking.todoListType" placeholder="Select To-Do List Type">
                        <el-option v-for="option in todoListTypeOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Notes">
                    <el-input type="textarea" v-model="editedBooking.notes"></el-input>
                </el-form-item>

            </div>

            <!-- step 1 Cohort -->
            <div v-show="currentStepEdit === 1">
                <el-form-item label="Partner School">
                    <el-radio-group v-model="editedBooking.partnerSchool">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Student Year">
                    <el-select v-model="editedBooking.studentYear" multiple>
                        <el-option v-for="year in studentYears" :key="year" :label="year" :value="year"
                            placeholder="Select Student Year"></el-option>
                    </el-select>
                    <!-- <el-checkbox-group v-model="editedBooking.studentYear">
                        <el-checkbox v-for="year in studentYears" :key="year" :label="year">{{ year }}</el-checkbox>
                    </el-checkbox-group> -->

                </el-form-item>
                <el-form-item label="Students # (Registered)">
                    <el-input v-model="editedBooking.regStudentsNo" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Students # (Attended)">
                    <el-input v-model="editedBooking.attendedStudentsNo" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Low SES">
                    <el-radio-group v-model="editedBooking.lowSes">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Accessibility Needs">
                    <el-input type="textarea" v-model="editedBooking.accNeeds"></el-input>
                </el-form-item>
                <el-form-item label="Allergy Needs">
                    <el-input type="textarea" v-model="editedBooking.allergyNeeds"></el-input>
                </el-form-item>
                <el-form-item label="Teacher Notes">
                    <el-input type="textarea" v-model="editedBooking.teacherNotes"></el-input>
                </el-form-item>
                <el-form-item label="Comments">
                    <el-input type="textarea" v-model="editedBooking.commentsSG"></el-input>
                </el-form-item>
            </div>


            <!-- step 2 Contact -->
            <div v-show="currentStepEdit === 2">
                <el-form-item label="First Name">
                    <el-input v-model="editedBooking.firstName"></el-input>
                </el-form-item>
                <el-form-item label="Last Name">
                    <el-input v-model="editedBooking.lastName"></el-input>
                </el-form-item>
                <el-form-item label="Email Address">
                    <el-input v-model="editedBooking.emailAddress"></el-input>
                </el-form-item>
                <el-form-item label="Phone Number">
                    <el-input v-model="editedBooking.phoneNumber"></el-input>
                </el-form-item>
                <el-form-item label="Teaching Area">
                    <el-input v-model="editedBooking.teachingArea"></el-input>
                </el-form-item>
            </div>

            <!-- step 3 Bus -->
            <div v-show="currentStepEdit === 3">
                <el-form-item label="Process Status">
                    <el-radio-group v-model="editedBooking.processStatus">
                        <el-radio label="contact">Contact Teacher</el-radio>
                        <el-radio label="delivered">Delivered</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Days Remaining">
                    <el-input v-model="daysRemaining" readonly></el-input>
                </el-form-item>
                <el-form-item label="Bus Status">
                    <el-select v-model="editedBooking.busStatus" placeholder="Select Bus Status">
                        <el-option v-for="option in busStatusOptions" :key="option.value" :label="option.label"
                            :value="option.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Quote">
                    <el-input v-model="editedBooking.quote" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Price Without GST">
                    <el-input v-model="editedBooking.priceWoGST" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Price Full">
                    <el-input v-model="editedBooking.priceFull" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Credit Surcharge">
                    <el-input v-model="editedBooking.creditSurcharge" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Date Paid">
                    <el-date-picker v-model="editedBooking.datePaid" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="Card Owner">
                    <el-input v-model="editedBooking.cardOwner"></el-input>
                </el-form-item>
                <el-form-item label="Bus Invoice No">
                    <el-input v-model="editedBooking.busInvoiceNo"></el-input>
                </el-form-item>
                <el-form-item label="Saved Receipt">
                    <el-radio-group v-model="editedBooking.savedReceipt">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Expense Master">
                    <el-radio-group v-model="editedBooking.expenseMaster">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="PIN Email">
                    <el-radio-group v-model="editedBooking.pinEmail">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Times in MSC">
                    <el-radio-group v-model="editedBooking.timesInMSC">
                        <el-radio label="Y">Yes</el-radio>
                        <el-radio label="N">No</el-radio>
                        <el-radio label="NA">NA</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Bus Notes">
                    <el-input type="textarea" v-model="editedBooking.busNotes"></el-input>
                </el-form-item>
            </div>


            <!-- step 4 Invoice -->
            <div v-show="currentStepEdit === 4">
                <el-form-item label="Amount">
                    <el-input v-model="editedBooking.amount"></el-input>
                </el-form-item>
                <el-form-item label="ABN">
                    <el-input v-model="editedBooking.abn"></el-input>
                </el-form-item>
                <el-form-item label="Invoice No">
                    <el-input v-model="editedBooking.invoiceNo"></el-input>
                </el-form-item>
            </div>


            <div class="form-bttns">
                <el-button @click="currentStepEdit--" v-show="currentStepEdit > 0">Back</el-button>
                <el-button @click="currentStepEdit++" v-show="currentStepEdit < 4">Next</el-button>
                <el-button type="primary" @click="saveChanges">Save Changes</el-button>
            </div>

        </el-form>


        <!-- <template #footer>
            <el-button @click="closeDialog">Back</el-button>
            <el-button type="primary" @click="saveChanges">Save Changes</el-button>
        </template> -->
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
import { statusOptions, programStreamOptions, deliveryLocationOptions, exhibitionOptions, todoListTypeOptions, busStatusOptions, studentYears, parseTime, formatTime } from './bookingUtils';

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

// const editedProgram = ref<User | null>(null);
// const editedBooking = ref<User | null>(null);
const editedBooking = ref({});
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
    // editedBooking.value = { ...program };
    editedBooking.value = { ...form.value };
    editDialogVisible.value = true;
};

// for view datails el-step
const currentStep = ref(0)
const resetCurrentStep = () => {
    currentStep.value = 0
}

// for edit booking el-step
const currentStepEdit = ref(0)
const resetCurrentStepEdit = () => {
    currentStepEdit.value = 0
}

const saveChanges = () => {
    // Save changes to editedProgram
    console.log("Saved changes:", editedBooking);
    editDialogVisible.value = false;
};

// placeholder data for view details
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

// placeholder data for edit booking
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
    exhibition: 'Non-Exhibition Linked',
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

// todo: modules should come from program list (active + upcoming programs)
const moduleOptions = ref([
    { label: 'W: Future Food', value: 'W: Future Food' },
    { label: 'W: Sustainable Communities', value: 'W: Sustainable Communities' },
    { label: 'Module 3', value: 'Module 3' },
])


// compute run time, is it really needed?
const runTime = computed(() => {
    if (editedBooking.value.startTime && editedBooking.value.endTime) {
        const start = parseTime(editedBooking.value.startTime)
        const end = parseTime(editedBooking.value.endTime)
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
    editedBooking.value.runTime = newVal
})

// compute days remaining
const daysRemaining = computed(() => {
    if (!editedBooking.value.programDate) {
        return 0
    }

    const programDate = new Date(editedBooking.value.programDate)
    const today = new Date()

    programDate.setHours(0, 0, 0, 0)
    today.setHours(0, 0, 0, 0)

    const diffTime = programDate.getTime() - today.getTime()
    const diffDays = Math.round(diffTime / (1000 * 60 * 60 * 24))

    return diffDays
})

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

.el-form {
    padding: 0 10px 0 10px;
}

.form-bttns {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
