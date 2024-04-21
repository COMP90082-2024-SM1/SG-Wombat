// Parent Component
<addNewProgram :form-visible.sync="isFormVisible" />
<template>
    <el-dialog :visible.sync="formVisible" title="Add New Program" width="700px">
      <el-form :model="form" label-width="120px">
        <!-- Form fields here -->
        <el-form-item label="Program Name">
          <el-input v-model="form.name" />
        </el-form-item>
  
        <el-form-item label="Max Amount of People">
          <el-input-number v-model="form.maxPeople" :min="1" />
        </el-form-item>
  
        <el-form-item label="Tech Requirement">
          <el-input v-model="form.techRequirement" />
        </el-form-item>
  
        <el-form-item label="Cost Per Person">
          <el-input-number v-model="form.costPerPerson" :min="0" />
        </el-form-item>
  
        <el-form-item label="Approximate Runtime">
          <el-select v-model="form.runtime" placeholder="Select runtime">
            <el-option label="1 hour" value="1 hour" />
            <el-option label="2 hours" value="2 hours" />
            <el-option label="3 hours" value="3 hours" />
          </el-select>
        </el-form-item>
  
        <el-form-item label="Description">
          <el-input type="textarea" v-model="form.description" />
        </el-form-item>
  
        <el-form-item label="Status">
          <el-radio-group v-model="form.status">
            <el-radio label="bookable">Bookable</el-radio>
            <el-radio label="not bookable">Not Bookable</el-radio>
          </el-radio-group>
        </el-form-item>
  
        <el-form-item label="Program State">
          <el-radio-group v-model="form.programState">
            <el-radio label="active">Active</el-radio>
            <el-radio label="archived">Archived</el-radio>
            <el-radio label="upcoming">Upcoming</el-radio>
          </el-radio-group>
        </el-form-item>
  
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Create Program</el-button>
          <el-button @click="onCancel">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </template>
  
  <script setup>
  import { reactive } from 'vue';
  import { defineProps, defineEmits } from 'vue';
  
  const props = defineProps({
    formVisible: {
      type: Boolean,
      default: false
    }
  });
  
  const emits = defineEmits(['update:formVisible']);
  
  const form = reactive({
    name: '',
    maxPeople: 1,
    techRequirement: '',
    costPerPerson: 0,
    runtime: '',
    description: '',
    status: 'not bookable',
    programState: 'active'
  });
  
  const onSubmit = () => {
    console.log('Submitted:', form);
    // Add API call logic here
  };
  
  const onCancel = () => {
    console.log('Cancelled');
    emits('update:formVisible', false); // This will update the parent's `isFormVisible` value to `false`
  };
  
  const updateVisibility = (value) => {
    emits('update:formVisible', value);
  };
  </script>
  