<template>


    <el-table ref="tableRef" row-key="date" :data="tableData" style="width: 100%">
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

  <!-- for view details by clicking on rows -->
  <el-dialog v-model="dialogDescVisible" title="Program Details" width="80%">
  <el-row gutter={20}>
    <!-- 左侧栏，保留现有内容 -->
    <el-col :span="12">
      <el-descriptions :column="1" :size="large" border>
        <el-descriptions-item label="Program Name">{{ ProgramDetails.programName }}</el-descriptions-item>
        <el-descriptions-item label="Maximum People">{{ ProgramDetails.maxPpl }}</el-descriptions-item>
        <el-descriptions-item label="Tech Requirement">{{ ProgramDetails.techReq }}</el-descriptions-item>
        <el-descriptions-item label="Cost per Person">{{ ProgramDetails.cost }}</el-descriptions-item>
        <el-descriptions-item label="Runtime">{{ ProgramDetails.runtime }}</el-descriptions-item>
        <el-descriptions-item label="Program Description">{{ ProgramDetails.programDesc }}</el-descriptions-item>
        <el-descriptions-item label="Available Days">{{ ProgramDetails.hostDays }}</el-descriptions-item>
        <el-descriptions-item label="Status">{{ ProgramDetails.programStatus }}</el-descriptions-item>
      </el-descriptions>
    </el-col>
    <!-- 右侧栏，新的 Checklist 内容 -->
    <el-col :span="12">
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

interface User {
    date: string
    name: string
    address: string
    tag: string
}

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

const tableData: User[] = [
    {
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Confirmed',
    },
    {
        date: '2016-05-02',
        name: 'Tom',
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
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Other',
    },
]

const selectedProgram = ref<User | null>(null);

// handleRowClick: to be deleted later if not use row click
const editedProgram = ref<User | null>(null);
const editDialogVisible = ref(false);


const showDetails = (program: User) => {
  selectedProgram.value = program;
  // detailsDialogVisible.value = true;
  dialogDescVisible.value = true
};

const showEditForm = (program: User) => {
  editedProgram.value = { ...program }; // Clone the program object
  editDialogVisible.value = true;
};

const ProgramDetails = reactive({
  programName: 'Program A',
  maxPpl: 20,
  techReq: "10 ipads",
  cost: 20,
  runtime: 3,
  programDesc: "Some description texts. Experience speculative local and international projects dissolving the line between what is considered natural and not natural. Curiosity, amusement, disgust – we invite you to look closely at your reactions and the thoughts behind them. In a world that is always hurrying forwards towards tech-based solutions, pause and consider where you stand. ",
  hostDays: "Tuesday, Wednesday, Thursday",
  programStatus: "Active"
})

const dialogDescVisible = ref(false);
const todoList = ref([]);

// 关闭弹窗的方法
const closeDialog = () => {
  dialogDescVisible.value = false;
};

// 保存 ToDo List 的方法
const saveTodoList = () => {
  console.log('Saved ToDo List:', todoList.value);
  // 在这里可以执行更多的保存逻辑，比如发送数据到服务器等
  closeDialog();  // Optionally close dialog after save
};


</script>

<style>
/* ToDo List 样式调整 */
.todo-list {
  padding: 20px;
  background-color: #1E1E1E; /* 深蓝背景 */
  color: white;
  border-radius: 8px;
}

.todo-header {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
}

.el-checkbox {
  margin-bottom: 10px;
  border-color: white;
  color: white;
}

.el-checkbox__label {
  font-size: 16px;
}

.el-checkbox__input.is-checked .el-checkbox__inner,
.el-checkbox__input.is-indeterminate .el-checkbox__inner {
  background-color: #409EFF; /* Element UI 蓝色 */
  border-color: #409EFF;
}

.el-button {
  margin-top: 20px;
}
</style>
