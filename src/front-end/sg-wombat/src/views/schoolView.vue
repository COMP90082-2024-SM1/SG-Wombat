<template>
    <el-card>
        <template #header>
            <div class="card-header">
                <span>Schools</span>
                <!-- <el-button>button</el-button> -->
                <el-button @click="showAddNewSchoolForm">Add New School</el-button>
                <addNewSchool v-model:formVisible="isFormVisible" />
                <!-- v-model'deault props is modelValue -->
            </div>
        </template>

        <div class="card-content">
            <schooltable class="listbox" />
        </div>
    </el-card>

</template>

<script lang="ts" setup>
// import { ArrowDown } from '@element-plus/icons-vue'
// import navigationMenu from './navBarNew.vue';
import schooltable from '@/components/schooltable.vue';
import addNewSchool from '@/components/addNewSchool.vue';
import { ref } from 'vue';
let isFormVisible = ref(false)
const showAddNewSchoolForm = () => {
    console.log("Attempting to show form");
    isFormVisible.value = true;
    console.log("Form visibility should now be true:", isFormVisible.value);
}
</script>


<style scoped>
.main-content {

    z-index: 1001;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 50px;

}
</style>