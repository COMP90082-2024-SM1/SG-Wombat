<template>
    <div class="background">
      <div class="bigcontainer">
        <div class="science">
          Science Gallery
        </div>
        <div class="content">
          <div class="innerbox">
            <location class="icon" />
            <div class="text">Dashboard</div>
          </div>
          <div class="innerbox">
            <document class="icon" />
            <div class="text">Booking</div>
          </div>
          <div class="innerbox">
            <chat-round class="icon" />
            <div class="text">Programs</div>
          </div>
          <div class="innerbox">
            <setting class="icon" />
            <div class="text">Room Information</div>
          </div>
          <div class="innerbox">
            <school class="icon" />
            <div class="text">Schools</div>
          </div>
          <div class="lastbox">
            <data-analysis class="icon" />
            <div class="text">Export Report</div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { Location, Document, ChatRound, Setting, School, DataAnalysis } from '@element-plus/icons-vue';
  
  export default {
    components: {
      Location,
      Document,
      ChatRound,
      Setting,
      School,
      DataAnalysis
    },
  }
  </script>
  
  <style scoped>
  @import '../assets/navigation_bar.css';
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
  
  .icon {
    /* 示例样式，根据需要调整 */
    font-size: 24px; /* 调整图标大小 */
    color: #409eff; /* 调整图标颜色 */
  }
  </style>
  