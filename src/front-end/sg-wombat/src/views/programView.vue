<template>
    <el-card>
        <template #header>
            <div class="card-header">
                <span>Programs</span>
                <!-- <el-button>button</el-button> -->
                <el-button @click="showAddNewProgramForm">Add New Program</el-button>
                <addNewProgram v-model:formVisible="isFormVisible" />
                <!-- v-model'deault props is modelValue -->
            </div>
        </template>

        <div class="card-content">
            <programtable class="listbox" />
        </div>
    </el-card>
</template>

<script lang="ts" setup>
// import { ArrowDown } from '@element-plus/icons-vue'
// import navigationMenu from './navBarNew.vue';
import { reactive, ref } from 'vue'
import programtable from '@/components/programtable.vue';
import addNewProgram from '@/components/addNewProgram.vue';
let isFormVisible = ref(false)
const showAddNewProgramForm = () => {
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