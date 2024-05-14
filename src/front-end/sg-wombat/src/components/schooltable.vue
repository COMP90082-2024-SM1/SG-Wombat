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
</template>

<script lang="ts" setup>
import { computed, ref } from 'vue'

interface School {
  schoolname: string
}

const search = ref('')
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
  console.log("Details of", row)
}

const tableData: School[] = [
  {
    schoolname: 'School A',
  },
  {
    schoolname: 'School B',
  },
  {
    schoolname: 'School C',
  },
  {
    schoolname: 'School D',
  },
]
</script>

  
  