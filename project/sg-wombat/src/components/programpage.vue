<script lang="ts" setup>
import { reactive, ref } from 'vue'
import NavigationBar from './newbar.vue'; // Ensure the import name matches the convention
import programtable from './programtable.vue';
import addNewProgram from './addNewProgram.vue';
let isFormVisible = ref(false)
const showAddNewProgramForm = () => {
    console.log("Attempting to show form");
    isFormVisible.value = true; 
    console.log("Form visibility should now be true:", isFormVisible.value);
}
</script>
<template>
    <div class="container">
        <NavigationBar class="navigation-panel" />
        <div class="dashboard-content">
            <programtable class="listbox" />
            <el-button @click="showAddNewProgramForm">Add New Program</el-button>
            <addNewProgram v-model:formVisible="isFormVisible" />
            <!-- v-model'deault props is modelValue -->
        </div>
    </div>
</template>
<style lang="scss" scoped>
.container {
    width: 100%;
    display: flex;
    flex-direction: row;
    height: 100vh;

    min-width: 1350px;
    /* border-radius: 30px */
}


.navigation-panel {
    /* position: absolute; */
    top: 0;
    left: 0;
    width: 30%;
    height: 100%;
    z-index: 1000;
}

.dashboard-content {

    background-color: green;
    height: 100vh;

    width: 60%;
   
    display: flex;
    flex-grow: 1;
    z-index: 1001;
}

.listbox {
    margin-top: 50px;
    margin-left: 30px;
    background-color: white;
    width: 1000px;
    height: 900px;
}
</style>