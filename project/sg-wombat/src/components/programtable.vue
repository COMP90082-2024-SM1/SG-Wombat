<template>
  <el-table :data="filterTableData" style="width: 100%">
    <el-table-column label="Program Name" prop="name" />
    <el-table-column label="Maximum People" prop="people" />
    <el-table-column align="right">
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
        <el-button
          size="small"
          type="danger"
          @click="deleteProgram(scope.row)"
        >
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
    <el-form :model="editedProgram" label-width="120px">
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
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue';

interface User {
  name: string
  people: string
  // Add other fields here to match your data structure
}

const search = ref('');
const tableData: User[] = [
  {
    name: 'Program A',
    people: '20',
    // Add other fields here to match your data structure
  },
  {
    name: 'Program B',
    people: '30',
    // Add other fields here to match your data structure
  },
  {
    name: 'Program C',
    people: '40',
    // Add other fields here to match your data structure
  },
  // Add more data as needed
];

const filterTableData = computed(() =>
  tableData.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
);

const detailsDialogVisible = ref(false);
const editDialogVisible = ref(false);
const selectedProgram = ref<User | null>(null);
const editedProgram = ref<User | null>(null);

const showProgramDetails = (program: User) => {
  selectedProgram.value = program;
  detailsDialogVisible.value = true;
};

const showEditForm = (program: User) => {
  editedProgram.value = { ...program }; // Clone the program object
  editDialogVisible.value = true;
};

const saveChanges = () => {
  // Save changes to editedProgram
  console.log("Saved changes:", editedProgram.value);
  editDialogVisible.value = false;
};

const deleteProgram = (program: User) => {
  // Delete the program from tableData
  const index = tableData.indexOf(program);
  if (index !== -1) {
    tableData.splice(index, 1);
  }
};
</script>




