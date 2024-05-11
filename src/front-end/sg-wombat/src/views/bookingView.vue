<template>
    <el-card>
        <template #header>
            <div class="card-header">
                <span>Bookings</span>
                            <!-- <el-button>button</el-button> -->
                <el-button @click="showAddNewbooking" class="Addnewbooking">Add New Booking</el-button>
                            <addNewbooking v-model:formVisible="isFormVisible" />
                            <!-- v-model'deault props is modelValue -->
            </div>
        </template>
            <div class="card-content">
                <BookingDetail class="listbox" />
            </div>

                        
                
    </el-card>

</template>

<script lang="ts" setup>
// import { ArrowDown } from '@element-plus/icons-vue'
// import navigationMenu from './navBarNew.vue';
import BookingDetail from '@/components/bookingdetail.vue';
import { reactive, ref } from 'vue'
import addNewbooking from '@/components/addNewBooking.vue';
let isFormVisible = ref(false)
const showAddNewbooking = () => {
    console.log("Attempting to show form");
    isFormVisible.value = true;
    console.log("Form visibility should now be true:", isFormVisible.value);
}



</script>

<style scoped>


.Addnewbooking{
    position: absolute;
    right: 40px;
}

.science {

    color: white;
    font-size: 40px;
    font-weight: bold;
    margin-top: 30px;
    margin-bottom: 20px;
    /* width: 50%; */
    /* margin-right: auto; */
    /* margin-left: 30px; */
    /* background-color: blueviolet; */
    /* width: 330px; */
    /* height: 56px; */
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.main-content {

    z-index: 1001;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;

}
</style>