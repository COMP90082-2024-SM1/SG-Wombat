<template>
    <div class="outcontainer">
        <div class="innercontainer">
            <el-container>
                <el-header>Booking Details</el-header>
                <div class="smallhead">
                    Teacher and School Details
                </div>
                <el-main>
                    <el-form label-position="top" class="booking-form">
                        <el-row :gutter="150">
                            <el-col :span="10">
                                <el-form-item label="First Name">
                                    <el-input placeholder="First Name"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="10" class="input1">
                                <el-form-item label="Last Name">
                                    <el-input placeholder="Last Name"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-form-item label="School">
                            <el-input placeholder="School"></el-input>
                        </el-form-item>
                        <el-form-item label="Email address">
                            <el-input placeholder="Email"></el-input>
                        </el-form-item>
                        <el-form-item label="Mobile number">
                            <el-input placeholder="Mobile number"></el-input>
                        </el-form-item>
                        <el-form-item label="Teaching area">
                            <el-input placeholder="Teaching area"></el-input>
                        </el-form-item>
                        <el-form-item label="Visit date">
                            <el-date-picker placeholder="Visit date"></el-date-picker>
                        </el-form-item>
                        <el-form-item class="buttom">
                            <el-button type="primary">Next</el-button>
                        </el-form-item>
                    </el-form>
                </el-main>
            </el-container>

        </div>
    </div>
</template>

<script>

</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

.outcontainer {

    max-width: 1920px;
    max-height: 1080px;
    width: 1920px;
    height: 1080px;
    background-color: #2E4DD4;
    justify-content: center;
    text-align: center;
    display: flex;
    font-family: 'Poppins', sans-serif;
}

.innercontainer {
    width: 70%;
    height: 100%;
    background-color: #eef1f6;


}

.booking-form {
    max-width: 90%;
    margin-top: -20px;
    font-size: 20px;
}


.buttom {
    /* background-color: #2E4DD4; */
    width: 30px;
    text-align: left;
    padding: 20px;
    margin-left: 950px;
    font-size: 20px
}

.smallhead {
    /* background-color: aqua; */
    width: 400px;
    text-align: left;
    padding: 20px;
    margin-left: 100px;
    font-size: 20px
}

.el-header {
    /* background-color: blueviolet; */
    color: #2E4DD4;
    width: 400px;
    text-align: left;
    margin-left: 100px;
    margin-top: 5%;
    padding: 30px 20px;
    font-weight: bolder;
    font-size: 40px;
}

.el-main {
    margin-left: 100px;
    margin-top: 5%;
    padding: 20px;
}

.el-form-item {
    margin-bottom: 25px;
}
</style>