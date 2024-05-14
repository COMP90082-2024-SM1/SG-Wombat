<template>
    <el-dialog v-model="visible" title="Add New School">
      <el-form :model="form" label-width="auto">
        <!-- Form fields here -->
        <el-form-item label="School Name">
          <el-input v-model="form.name" />
        </el-form-item>

        <el-form-item label="Postcode">
          <el-input type="textarea" v-model="form.postcode" />
        </el-form-item>

        <el-form-item label="ICSEA">
          <el-input type="textarea" v-model="form.icsea" />
        </el-form-item>

        <el-form-item label="ABN Number">
          <el-input type="textarea" v-model="form.number" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">Create New School</el-button>
          <el-button @click="onCancel">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </template>
  
  <script setup>
  import { reactive, computed } from 'vue';
  import { defineProps, defineEmits } from 'vue';
  
  const props = defineProps({
    formVisible: Boolean
  });
  
  const emits = defineEmits(['update:formVisible']);
  
  const visible = computed({
    get: () => props.formVisible,
    set: (value) => emits('update:formVisible', value)
  });
  
  const form = reactive({
      name: '',
      postcode: '',
      icsea: '',  
      number:'',    
    
  });
  
  const onSubmit = () => {
    console.log('Submitted:', form);
    visible.value = false;  // Close the dialog
  };
  
  const onCancel = () => {
    visible.value = false;
    console.log('Cancelled');
  };
  </script>