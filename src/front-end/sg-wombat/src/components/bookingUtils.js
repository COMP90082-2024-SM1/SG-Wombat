import { ref } from 'vue';

const programStreamOptions = ref([
    { label: 'SCoE: Excursions', value: 'SCoE: Excursions' },
    { label: 'ART: Community', value: 'ART: Community' },
    { label: 'ART: Digital Program', value: 'ART: Digital Program' },
    { label: 'ART: Excursions', value: 'ART: Excursions' },
    { label: 'ART: Teacher PL', value: 'ART: Teacher PL' },
    { label: 'SCoE: Community', value: 'SCoE: Community' },
    { label: 'SCoE: Digital Program', value: 'SCoE: Digital Program' },
    { label: 'SCoE: Teacher PL', value: 'SCoE: Teacher PL' },
    { label: 'STEAM: Community', value: 'STEAM: Community' },
    { label: 'STEAM: Digital Program', value: 'STEAM: Digital Program' },
    { label: 'STEAM: Excursions', value: 'STEAM: Excursions' },
    { label: 'STEAM: ISSP', value: 'STEAM: ISSP' },
    { label: 'STEAM: Special Outreach Projects', value: 'STEAM: Special Outreach Projects' },
    { label: 'STEAM: Teacher PL', value: 'STEAM: Teacher PL' },
]);


const statusOptions = ref([
    { label: 'Request', value: 'Request' },
    { label: 'Processing', value: 'Processing' },
    { label: 'Delivered', value: 'Delivered' },
    { label: 'Postponed', value: 'Postponed' },
    { label: 'Cancelled', value: 'Cancelled' },
    { label: 'Upcoming', value: 'Upcoming' },
    { label: 'Turned Away (Resourcing)', value: 'Turned Away (Resourcing)' },
    { label: 'Turned Away (Primary)', value: 'Turned Away (Primary)' },
]);

const deliveryLocationOptions = ref([
    { label: 'SGM: SGMT', value: 'SGM: SGMT' },
    { label: 'SGM: EG', value: 'SGM: EG' },
    { label: 'SGM: WG', value: 'SGM: WG' },
    { label: 'SGM: PT', value: 'SGM: PT' },
    { label: 'SGM: W2', value: 'SGM: W2' },
    { label: 'SGM: W3', value: 'SGM: W3' },
    { label: 'Buxton', value: 'Buxton' },
    { label: 'Grainger', value: 'Grainger' },
    { label: 'Ian Potter', value: 'Ian Potter' },
    { label: 'Old Quad', value: 'Old Quad' },
    { label: 'Incursion', value: 'Incursion' },
    { label: 'Embedded (25%)', value: 'Embedded (25%)' },
    { label: 'Online: Asynchronous', value: 'Online: Asynchronous' },
    { label: 'Online: Synchronous', value: 'Online: Synchronous' },
    { label: 'SGM: MiniBleachers', value: 'SGM: MiniBleachers' },
    { label: 'SGM: CAFÉ', value: 'SGM: CAFÉ' },
]);

const exhibitionOptions = ref([
    { label: 'Not Natural', value: 'Not Natural' },
    { label: 'Ancient Lives', value: 'Ancient Lives' },
    { label: 'Non-Exhibition Linked', value: 'Non-Exhibition Linked' },
    { label: 'Science Fiction', value: 'Science Fiction' },
    { label: 'Nadine Christensen', value: 'Nadine Christensen' },
    { label: 'The Arena', value: 'The Arena' },
]);

const todoListTypeOptions = ref([
    { label: 'Todo List Template1', value: 'Todo List Template1' },
    { label: 'Todo List Template2', value: 'Todo List Template2' },
    { label: 'Todo List Template3', value: 'Todo List Template3' },
])


const busStatusOptions = ref([
    { label: 'Requested Quote', value: 'Quote' },
    { label: 'Processing', value: 'Processing' },
    { label: 'Paid', value: 'Paid' },
    { label: 'Cancelled', value: 'Cancelled' },
    { label: 'Awaiting reply from Teacher', value: 'AwaitingTeacher' },
    { label: 'Split Payement', value: 'SplitPayement' },
]);

const studentYears = ref(['7', '8', '9', '10', '11', '12', 'VCE', 'VCE Vocational Major or VET']);


const parseTime = (time) => {
    const [hours, minutes] = time.split(':').map(Number)
    return hours * 3600 + minutes * 60
}

const formatTime = (seconds) => {
    const h = Math.floor(seconds / 3600).toString().padStart(2, '0')
    const m = Math.floor((seconds % 3600) / 60).toString().padStart(2, '0')
    return `${h}:${m}`
}

export { programStreamOptions, statusOptions, deliveryLocationOptions, exhibitionOptions, todoListTypeOptions, busStatusOptions, studentYears, parseTime, formatTime};