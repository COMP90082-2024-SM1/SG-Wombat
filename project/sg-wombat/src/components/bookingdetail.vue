<template>


    <el-table ref="tableRef" row-key="date" :data="tableData" style="width: 100%">
        <el-table-column prop="date" label="Date" sortable width="120" column-key="date" :filters="[
        { text: '2016-05-01', value: '2016-05-01' },
        { text: '2016-05-02', value: '2016-05-02' },
        { text: '2016-05-03', value: '2016-05-03' },
        { text: '2016-05-04', value: '2016-05-04' },
    ]" :filter-method="filterHandler" />
        <el-table-column prop="name" label="Name" width="120" />
        <el-table-column prop="address" label="Address" :formatter="formatter" />


        <el-table-column prop="tag" label="All Bookings" width="150" :filters="[
        { text: 'Confirmed Bookings', value: 'Confirmed' },
        { text: 'Other Bookings', value: 'Other' },
    ]" :filter-method="filterTag" filter-placement="bottom-end">
            <template #default="scope">
                <el-tag :type="scope.row.tag === 'Other' ? '' : 'success'" disable-transitions>{{ scope.row.tag
                    }}</el-tag>
            </template>
        </el-table-column>
    </el-table>
    <el-button @click="resetDateFilter">Reset Date</el-button>
    <el-button @click="clearFilter">Reset All</el-button>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import type { TableColumnCtx, TableInstance } from 'element-plus'

interface User {
    date: string
    name: string
    address: string
    tag: string
}

const tableRef = ref<TableInstance>()

const resetDateFilter = () => {
    tableRef.value!.clearFilter(['date'])
}

const filterData = (tag: string) => {
    if (tableRef.value) {
        // 正确调用 filter 方法，明确指定要筛选的列和值
        tableRef.value.filter('tag', tag);
    }
}


// TODO: improvement typing when refactor table
const clearFilter = () => {
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-expect-error
    tableRef.value!.clearFilter()
}
const formatter = (row: User, column: TableColumnCtx<User>) => {
    return row.address
}
const filterTag = (value: string, row: User) => {
    return row.tag === value
}
const filterHandler = (
    value: string,
    row: User,
    column: TableColumnCtx<User>
) => {
    const property = column['property']
    return row[property] === value
}

const tableData: User[] = [
    {
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Confirmed',
    },
    {
        date: '2016-05-02',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Other',
    },
    {
        date: '2016-05-04',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Confirmed',
    },
    {
        date: '2016-05-01',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        tag: 'Other',
    },
]
</script>

<style>

</style>