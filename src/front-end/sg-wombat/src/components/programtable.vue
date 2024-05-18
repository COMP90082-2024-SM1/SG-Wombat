<template>
  <el-table :data="tableData2" style="width: 100%" @row-click="handleRowClick">
    <el-table-column label="Program Name" width="auto" prop="name" />

    <el-table-column label="Maximum People" prop="people" />
    <el-table-column label="Status" prop="status" />
    <el-table-column width="auto" align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button size="small" @click="showProgramDetails(scope.row)">
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

  <!-- Program Details Dialog -->
  <el-dialog v-model="detailsDialogVisible" title="Program Details">
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
  <el-dialog v-model="dialogDescVisible" title="Program Details" width="50%">
    <el-descriptions :column="1" :size="large" border>
      <el-descriptions-item label="Program Name">{{
        ProgramDetails.name
      }}</el-descriptions-item>
      <el-descriptions-item label="Maximum People">{{
        ProgramDetails.maxCap
      }}</el-descriptions-item>
      <el-descriptions-item label="Tech Requirement">{{
        ProgramDetails.techReq
      }}</el-descriptions-item>
      <el-descriptions-item label="Cost per Person">{{
        ProgramDetails.costPerson
      }}</el-descriptions-item>
      <el-descriptions-item label="Runtime">{{
        ProgramDetails.duration
      }}</el-descriptions-item>
      <el-descriptions-item label="Program Description">{{
        ProgramDetails.description
      }}</el-descriptions-item>
      <el-descriptions-item label="Available Days">{{
        ProgramDetails.avaliableDays
      }}</el-descriptions-item>
      <el-descriptions-item label="Status">{{
        ProgramDetails.status
      }}</el-descriptions-item>

    </el-descriptions>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, computed, reactive, watch } from "vue";
import axios from "axios";
import { useRouter, useRoute } from "vue-router";

interface User {
  name: string;
  people: string;
  status: string;
  maxPpl: string;
  techReq: string;
  cost: string;
  runtime: string;
  programDesc: string;
  hostDays: string;
  programStatus:string;
  // Add other fields here to match your data structure
}

const search = ref("");
const tableData: User[] = [
  {
    name: "Program A",
    people: "20",
    status: "Active",
    // Add other fields here to match your data structure
  },
  {
    name: "Program B",
    people: "30",
    status: "Upcoming",
    // Add other fields here to match your data structure
  },
  {
    name: "Program C",
    people: "40",
    status: "Archived",
    // Add other fields here to match your data structure
  },
  // Add more data as needed
];

const router = useRouter();
const route = useRoute();
const tableData2 = ref<User[]>([]);
const ProgramDetails = ref<User>({})

watch(
  () => route.name,
  (newRouteName) => {
    axios.get("/progs").then((r) => {
      console.log(r.data.data)
      tableData2.value = r.data.data;
    });
  },
  { immediate: true }
);

const filterTableData = computed(() => {
  console.log('call')
  axios.get("/progs").then((r) => {
    console.log(r.data.data);
    tableData2.value = r.data.data;
  });
});


const detailsDialogVisible = ref(false);
const editDialogVisible = ref(false);
const selectedProgram = ref<User | null>(null);
const editedProgram = ref<User | null>(null);

const showProgramDetails = (program: User) => {
  selectedProgram.value = program;
  // detailsDialogVisible.value = true;
  dialogDescVisible.value = true;

  ProgramDetails.value = program
  console.log(">"+JSON.stringify(program))
};

const showEditForm = (program: User) => {
  editedProgram.value = { ...program }; // Clone the program object
  editDialogVisible.value = true;
};

const saveChanges = () => {
  // Save changes to editedProgram
  console.log("Saved changes:", editedProgram.value);
  editDialogVisible.value = false;

  axios.put('progs', {
    progId:editedProgram.value.progId,
    name: editedProgram.value.name,        // 参数 firstName
    costPerson: editedProgram.value.costPerson
  })
  .then(function (response) {
    console.log(response);
    window.location.reload()
  })
  .catch(function (error) {
    console.log(error);
  });
};

const deleteProgram = (program: User) => {
  // Delete the program from tableData
  // const index = tableData.indexOf(program);
  // if (index !== -1) {
  //   tableData.splice(index, 1);
  // }

  axios.delete(`progs/${program.progId}`)
    .then(function (response) {
    console.log(response);
     window.location.reload()
   })
   .catch(function (error) {
    console.log(error);
  });

};

// for view details by clicking on rows
const dialogDescVisible = ref(false);
// handleRowClick: to be deleted later if not use row click
const handleRowClick = (row: User) => {
  console.log("Clicked row:", row);
  // dialogDescVisible.value = true
};


</script>
