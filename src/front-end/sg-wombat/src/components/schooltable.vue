<template>
  <el-table :data="currentPageData" style="width: 100%">

    <el-table-column label="School Name" prop="name" />

    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button
          size="small"
          @click="handleShowDetails(scope.$index, scope.row)"
        >
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

  <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="currentPage"
    :page-sizes="[5, 10, 20]"
    :page-size="pageSize"
    layout="total, sizes, prev, pager, next, jumper"

    :total="filterTableData?.length"

  ></el-pagination>

  <!-- Dialog for showing school details -->
  <el-dialog v-model="dialogVisible" title="School Details">
    <p>School Name: {{ selectedSchool?.name }}</p>
    <p>Postcode: {{ selectedSchool?.postCode }}</p>
    <p>ICSEA: {{ selectedSchool?.icsea }}</p>
    <p>ABN Number {{ selectedSchool?.abnNum }}</p>
  </el-dialog>

  <!-- Dialog for editing school details -->
  <el-dialog v-model="editDialogVisible" title="Edit School">
    <!-- Add form elements to edit school details here -->
    <el-form :model="editedSchool" label-width="200px">
      <el-form-item label="School Name">
        <el-input v-model="editedSchool.name" />
      </el-form-item>
      <el-form-item label="Postcode">
        <el-input v-model="editedSchool.postCode" />
      </el-form-item>
      <el-form-item label="ICSEA">
        <el-input v-model="editedSchool.icsea" />
      </el-form-item>
      <el-form-item label="ABN Number">
        <el-input v-model="editedSchool.abnNum" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="editDialogVisible = false">Cancel</el-button>
      <el-button type="primary" @click="saveEdit">Save</el-button>
    </div>
  </el-dialog>
</template>

<script setup lang="ts">
import { computed, ref, watch } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";

interface School {

  schoolname: string;
  postcode: string;
  icsea: string;
  number: string;

}

const search = ref("");
const dialogVisible = ref(false);
const editDialogVisible = ref(false);
const currentPage = ref(1);
const pageSize = ref(5);

const selectedSchool = ref<School | null>(null);
const editedSchool = ref<School | null>(null);
// let selectedSchool: School | null = null;
// let editedSchool: School | null = null;
const tableData = ref<School[]>([]);
const SchoolDetails = ref<School>({});

const router = useRouter();
const route = useRoute();

watch(
  () => route.name,
  (newRouteName) => {
    axios.get("/schools").then((r) => {
      console.log(r?.data?.data);
      tableData.value = r.data.data;
    });
  },
  { immediate: true }
);


const filterTableData = computed(() =>{
console.log(JSON.stringify(tableData.value)+" ??")

  tableData?.value?.rows?.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
})

const currentPageData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  //return tableData.value.rows.slice(start, end);
  return tableData.value.rows;
});

const handleSizeChange = (val: number) => {
  pageSize.value = val;
};

const handleCurrentChange = (val: number) => {
  currentPage.value = val;
};

const currentPageData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return filterTableData.value.slice(start, end);
})

const handleSizeChange = (val: number) => {
  pageSize.value = val;
}

const handleCurrentChange = (val: number) => {
  currentPage.value = val;
}

const handleEdit = (index: number, row: School) => {
  console.log('edit')
  selectedSchool.value = row;
  editedSchool.value = row; // Make a copy of the selected school for editing
  editDialogVisible.value = true;
};

const handleDelete = (index: number, row: School) => {
  console.log(index, row);

  axios.delete(`schools/${row.schoolId}`)
    .then(function (response) {
      console.log(response);
      window.location.reload();
    })
    .catch(function (error) {
      console.log(error);
    });
};

const handleShowDetails = (index: number, row: School) => {
  selectedSchool.value = row;
  editedSchool.value = row
  dialogVisible.value = true;
};

const saveEdit = () => {
  if (selectedSchool && editedSchool) {
   // Object.assign(selectedSchool, editedSchool);
    editDialogVisible.value = false;

    axios
      .put("/schools", {
        abnNum: editedSchool.value.abnNum,
        icsea: editedSchool.value.icsea,
        name: editedSchool.value.name,
        postCode: editedSchool.value.postCode,
        schoolId: editedSchool.value.schoolId,
      })
      .then(function (response) {
        window.location.reload();
      })
      .catch(function (error) {
        console.log(error);
      });
  }
};

</script>

