<template>
    <el-table :data="filterTableData" style="width: 100%">
      <el-table-column label="Program Name" prop="name" />
      <el-table-column label="Maximum Number" prop="maximum" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="small" placeholder="Type to search" />
        </template>
        <template #default="scope">
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
  
  interface User {
    name: string
    people: string
  }
  
  const search = ref('')
  const filterTableData = computed(() =>
    tableData.filter(
      (data) =>
        !search.value ||
        data.name.toLowerCase().includes(search.value.toLowerCase())
    )
  )
  const handleEdit = (index: number, row: User) => {
    console.log(index, row)
  }
  const handleDelete = (index: number, row: User) => {
    console.log(index, row)
  }
  
  const tableData: User[] = [
    {
      name: 'ProgramA',
      people: '20',
    },
    {
      name: 'ProgramB',
      people: '40',
    },
    {
      name: 'ProgramC',
      people: '30',
    },
    
  ]
  </script>