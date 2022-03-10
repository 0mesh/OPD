var docData: any[] = [
  {
    DoctorId: 'D000004',
    DoctorName: 'Vinod Patil',
    Speciality: 'NEUROLOGIST',
  },
  {
    DoctorId: 'D000005',
    DoctorName: 'Vikram Batra',
    Speciality: 'CARDIOLOGIST',
  },
  {
    DoctorId: 'D000006',
    DoctorName: 'Omesh Agrawal',
    Speciality: 'NEUROLOGIST',
  },
  {
    DoctorId: 'D000007',
    DoctorName: 'Ramesh Agrawal',
    Speciality: 'ENT',
  },
  {
    DoctorId: 'D000008',
    DoctorName: 'Karan Pandit',
    Speciality: 'CARDIOLOGIST',
  },
  {
    DoctorId: 'D000009',
    DoctorName: 'Ankit Autkar',
    Speciality: 'OPTHAMOLOGIST',
  },
  {
    DoctorId: 'D000010',
    DoctorName: 'Radhika Sharma',
    Speciality: 'G_PHYSICIAN',
  },
  {
    DoctorId: 'D000011',
    DoctorName: 'Rita Yadav',
    Speciality: 'ORTHOPAEDIC',
  },
  {
    DoctorId: 'D000012',
    DoctorName: 'Sharayu Yadav',
    Speciality: 'NEUROLOGIST',
  },
  {
    DoctorId: 'D000013',
    DoctorName: 'Yash kumar',
    Speciality: 'PULMONOLOGIST',
  },
  {
    DoctorId: 'D000014',
    DoctorName: 'Manish Malhotra',
    Speciality: 'GYNAECOLOGIST',
  },
  {
    DoctorId: 'D000015',
    DoctorName: 'Kamla Malhotra',
    Speciality: 'GYNAECOLOGIST',
  },
];
interface Doctor {
  doctorName: string;
  doctorId: string;
  doctorNameID: string;
}
// var doctorMap = new Map<string, Array<Map<string, string>>>();
var doctorMapFinal = new Map<string, Array<Doctor>>();
for (var c of docData) {
  let speciality = c['Speciality'];
  let docID = c['DoctorId'];
  let concatenated = docID + '-' + c['DoctorName'];
  let maptoPush = new Map<string, string>().set(concatenated, docID);
  let eletoPush = {
    doctorName: c['DoctorName'],
    doctorId: docID,
    doctorNameID: concatenated,
  };
  if (doctorMapFinal.has(speciality)) {
    // let arrayDocs = doctorMap.get(speciality);
    let temparrayDocs = doctorMapFinal.get(speciality);
    // arrayDocs.push(maptoPush);
    temparrayDocs.push(eletoPush);
    // doctorMap.delete(speciality);
    doctorMapFinal.delete(speciality);
    // doctorMap.set(speciality, arrayDocs);
    doctorMapFinal.set(speciality, temparrayDocs);
  } else {
    let arraytoPush = new Array<Map<string, string>>();
    let temparraytoPush = new Array<Doctor>();
    temparraytoPush.push(eletoPush);
    arraytoPush.push(maptoPush);
    // doctorMap.set(speciality, arraytoPush);
    doctorMapFinal.set(speciality, temparraytoPush);
  }
}

export { doctorMapFinal, Doctor };
