<template>
  <el-table :data="filterTableData" style="width: 100%">
    <el-table-column label="School Name" prop="schoolname" />
    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button size="small" @click="handleShowDetails(scope.$index, scope.row)">
          Show Details
        </el-button>
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
          Edit
        </el-button>
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
        >
          Delete
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <!-- Dialog for showing school details -->
  <el-dialog v-model="dialogVisible" title="School Details">
    <p>School Name: {{ selectedSchool?.schoolname }}</p>
    <p>ABN Number: {{ selectedSchool?.abn }}</p>
    <p>Location: {{ selectedSchool?.location }}</p>
  </el-dialog>
  <!-- Dialog for editing school details -->
  <el-dialog v-model="editDialogVisible" title="Edit School">
    <!-- Add form elements to edit school details here -->
    <el-form :model="editedSchool" label-width="100px">
      <el-form-item label="School Name">
        <el-input v-model="editedSchool.schoolname" />
      </el-form-item>
      <el-form-item label="ABN Number">
        <el-input v-model="editedSchool.abn" />
      </el-form-item>
      <el-form-item label="Location">
        <el-input v-model="editedSchool.location" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="editDialogVisible = false">Cancel</el-button>
      <el-button type="primary" @click="saveEdit">Save</el-button>
    </div>
  </el-dialog>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'

interface School {
  schoolname: string
  abn: string
  location: string
}

const search = ref('')
const dialogVisible = ref(false);
const editDialogVisible = ref(false);
let selectedSchool: School | null = null;
let editedSchool: School | null = null;

const filterTableData = computed(() =>
  tableData.filter(
    (data) =>
      !search.value ||
      data.schoolname.toLowerCase().includes(search.value.toLowerCase())
  )
)

const handleEdit = (index: number, row: School) => {
  selectedSchool = row;
  editedSchool = { ...row }; // Make a copy of the selected school for editing
  editDialogVisible.value = true;
}

const handleDelete = (index: number, row: School) => {
  console.log(index, row)
}

const handleShowDetails = (index: number, row: School) => {
  selectedSchool = row;
  dialogVisible.value = true;
}

const saveEdit = () => {
  if (selectedSchool && editedSchool) {
    Object.assign(selectedSchool, editedSchool);
    editDialogVisible.value = false;
  }
}

const tableData: School[] = [
  {
    schoolname: 'School A',
    abn: '12345678901',
    location: 'Location A'
  },
  {
    schoolname: 'School B',
    abn: '23456789012',
    location: 'Location B'
  },
  {
    schoolname: 'School C',
    abn: '34567890123',
    location: 'Location C'
  },
  {
    schoolname: 'School D',
    abn: '45678901234',
    location: 'Location D'
  },
]
</script>






  
  