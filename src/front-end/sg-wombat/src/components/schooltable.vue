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
let selectedSchool: School | null = null;

const filterTableData = computed(() =>
  tableData.filter(
    (data) =>
      !search.value ||
      data.schoolname.toLowerCase().includes(search.value.toLowerCase())
  )
)

const handleEdit = (index: number, row: School) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: School) => {
  console.log(index, row)
}
const handleShowDetails = (index: number, row: School) => {
  selectedSchool = row;
  dialogVisible.value = true;
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




  
  