package sp_portal;
import sp_portal.*;
import sp_portal.local.*;
import org.joda.time.LocalDate;

class DataSetupHelper {

    def adminRole
    def userRole

    def adminUser
    def normalUser
	def normalUser2

    def standardizedPatient1
	def standardizedPatient2
    def standardizedPatient3
	def bankaccount1
	
	def anamnesisForm1	
	def anamnesisCheckTitle1	
	def anamnesisCheck1
	def anamnesisCheck2
	def anamnesisChecksValue1
	def anamnesisCheck3
	def training1
	def training2
	def training3
	def osce1
	def osce2
	def osce3
	
	def osceDay1

	def email1
	def email2
	
	def osce
	def semester
	
	
	// is standardizedPatient2 has no osces or trainings
	def emptyPatientInSemester 
	
	// is standardizedPatient1 has one osce and one training
	def patientInSemester
	
    def getDataSetA(){
        setupRoles()
        setupUsers()
        standardizedPatient1 =  setupStandardizedPatients("")
		setupAnamnesisCheckTitle()
		setupAnamnesisCheck1()
		setupAnamnesisCheck2()
		setupAnamnesisCheck3()
		//setUpTraining1()
    	//setUpOsceDay1()
		anamnesisCheck1.anamnesisCheckTitle = anamnesisCheckTitle1
		anamnesisCheck2.anamnesisCheckTitle = anamnesisCheckTitle1
		setupAnamnesisForm()

        assertNotNull normalUser

        normalUser.standardizedPatient = standardizedPatient1


        normalUser.save()

        assertNotNull normalUser.standardizedPatient

        setupBankAccounts()

        standardizedPatient1.bankaccount = bankaccount1
		standardizedPatient1.anamnesisForm = anamnesisForm1
        standardizedPatient1.save()

		
		setupAnamnesisCheckValue1()

        assertNotNull normalUser.standardizedPatient.bankaccount



    }



	/**
	 * Data set B has 2 setupStandardizedPatients, they currently both share a bank account and 
	 * anamnesisForm but you can change this if you need to.
	 */
   def getDataSetB(){
        setupRoles()
        setupUsers()
        standardizedPatient1 =  setupStandardizedPatients("")
		standardizedPatient2 =  setupStandardizedPatients("B")
		//standardizedPatient3 =  setupStandardizedPatients("a")
		setupAnamnesisCheckTitle()
		setupAnamnesisCheck1()
		setupAnamnesisCheck2()
		setupAnamnesisCheck3()
		anamnesisCheck1.anamnesisCheckTitle = anamnesisCheckTitle1
		anamnesisCheck2.anamnesisCheckTitle = anamnesisCheckTitle1
		setupAnamnesisForm()

        assertNotNull normalUser

        normalUser.standardizedPatient = standardizedPatient1
		normalUser2.standardizedPatient = standardizedPatient2

        normalUser.save()

        assertNotNull normalUser.standardizedPatient

        setupBankAccounts()

        standardizedPatient1.bankaccount = bankaccount1
		standardizedPatient1.anamnesisForm = anamnesisForm1
        standardizedPatient1.save()

        standardizedPatient2.bankaccount = bankaccount1
		standardizedPatient2.anamnesisForm = anamnesisForm1
        standardizedPatient2.save()
		
		//standardizedPatient3.bankaccount = bankaccount1
		//standardizedPatient3.anamnesisForm = anamnesisForm1
        //standardizedPatient3.save()

		
		setupAnamnesisCheckValue1()

        assertNotNull normalUser.standardizedPatient.bankaccount

		email1 = setupEmail("1")
		email2 = setupEmail("2")
    }




//////////////////////////////////////////////////////////////////////////////////
    def setupRoles(){

       if (Role.list().size() == 0){

            adminRole = new Role();
            adminRole.roleName = "ADMIN_ROLE";
            adminRole.roleDescription = "Administrate Users";
            adminRole.save();

            userRole = new Role();
            userRole.roleName = "USER_ROLE";
            userRole.roleDescription = "Normal Users";

            userRole.save();
       }
    }




    def setupUsers(){

        User admin = new User();


        admin.userName = "AdminUser";
        admin.userEmail = "Admin@user";
        admin.passwordHash = "not hashed";
        admin.isActive = true;

        def roles0 = [];
        roles0.add(Role.findByRoleName("ADMIN_ROLE"));

		admin.roles = roles0;

		admin.save();

        adminUser = admin

//////////////////////////////////////////////////////////////////
        User user1 = new User();

        user1.userName = "NormalUser";
        user1.userEmail = "Normal@user.cn";
        user1.passwordHash = "not hashed";
        user1.isActive = true;

        def roles1 = [];
        roles1.add(Role.findByRoleName("USER_ROLE"));

        user1.roles = roles1;

        user1.save();

        normalUser = user1
//////////////////////////////////////////////////////////////////		
		
		User user2 = new User();

        user2.userName = "NormalUser2";
        user2.userEmail = "Normal2@user.cn";
        user2.passwordHash = "not hashed2";
        user2.isActive = true;

        roles1 = [];
        roles1.add(Role.findByRoleName("USER_ROLE"));

        user2.roles = roles1;

        user2.save();

        normalUser2 = user2
    }

    def setupStandardizedPatients(def prefix){
        StandardizedPatient standardizedPatient = new StandardizedPatient();
        standardizedPatient.origId = 1;
        standardizedPatient.birthday = new LocalDate();
        standardizedPatient.city = "${prefix}Wuhu"
        standardizedPatient.email = "${prefix}sp1@test.com"
        standardizedPatient.gender = 1
        standardizedPatient.height = 81
        standardizedPatient.immagePath = "${prefix}/aa/bb"
        standardizedPatient.maritalStatus = 1
        standardizedPatient.mobile = "123454567"
        standardizedPatient.name = "smith"
        standardizedPatient.postalCode = 123456789
        standardizedPatient.preName = "bob"
        standardizedPatient.socialInsuranceNo = "1234567890123"
        standardizedPatient.street = "${prefix}a street"
        standardizedPatient.telephone = "123454567"
        standardizedPatient.telephone2 = "123454567"
        standardizedPatient.videoPath = "123454567"
        standardizedPatient.weight = 79
        standardizedPatient.workPermission = null
        standardizedPatient.anamnesisForm  = null
        standardizedPatient.description  = null
        standardizedPatient.profession  = null
        standardizedPatient.nationality  = null
        standardizedPatient.bankaccount  = null

        standardizedPatient.save();
		
		return standardizedPatient
        

    }
	 

    def setupBankAccounts(){
        def bankaccount = new Bankaccount()
        bankaccount.bic = 'jfskhfsdhj'
        bankaccount.iban = '132654987454654'
        bankaccount.bankName = 'ICBC'
        bankaccount.city = 'Wuhu'
        bankaccount.ownerName = 'owner'
        bankaccount.postalCode = 234567891
        bankaccount.origId = 5

        bankaccount.save();

        bankaccount1 = bankaccount;

    }

	def setupAnamnesisForm(){
		def anamnesisForm = new AnamnesisForm()
		anamnesisForm.createDate = new Date()
		anamnesisForm.origId = 2
		
		anamnesisForm.save();
		
		anamnesisForm1 = anamnesisForm;
	}
	
	def setupAnamnesisCheckTitle(){
		def anamnesisCheckTitle = new AnamnesisCheckTitle();
		anamnesisCheckTitle.text = 'title1'
		anamnesisCheckTitle.sortOrder = 1
		
		anamnesisCheckTitle.save();
		
		anamnesisCheckTitle1 = anamnesisCheckTitle;
	}
	
	def setupAnamnesisCheck1(){
		def anamnesisCheck = new AnamnesisCheck();
		anamnesisCheck.text = 'Rauchen Sie?'
		anamnesisCheck.value = ''
		anamnesisCheck.sortOrder = 1
		anamnesisCheck.type = 1   // should be a boolean type
		anamnesisCheck.anamnesisCheckTitle = null
		anamnesisCheck.origId = 2
		
		anamnesisCheck.save();
		
		anamnesisCheck1 = anamnesisCheck;

	}
	
	def setupAnamnesisCheck2(){
		def anamnesisCheck = new AnamnesisCheck();
		anamnesisCheck.text = 'Leiden Sie unter Diabetes?'
		anamnesisCheck.value = ''
		anamnesisCheck.sortOrder = 1
		anamnesisCheck.type = 1
		anamnesisCheck.anamnesisCheckTitle = null
		anamnesisCheck.origId = 2
		
		anamnesisCheck.save();
		
		anamnesisCheck2 = anamnesisCheck;

	}
	
	def setupAnamnesisCheck3(){
		def anamnesisCheck = new AnamnesisCheck();
		anamnesisCheck.text = 'Describe your best ever holiday'
		anamnesisCheck.value = ''
		anamnesisCheck.sortOrder = 1
		anamnesisCheck.type = 0 // should be a string type according to yyb
		anamnesisCheck.anamnesisCheckTitle = null
		anamnesisCheck.origId = 2
		
		anamnesisCheck.save();
		
		anamnesisCheck3 = anamnesisCheck;

	}
	
	
	def setupAnamnesisCheckValue1(){
		def anamnesisChecksValue = new AnamnesisChecksValue();
		anamnesisChecksValue.origId = 5
		anamnesisChecksValue.comment = null;
		anamnesisChecksValue.truth = false
		anamnesisChecksValue.anamnesisForm = anamnesisForm1
		anamnesisChecksValue.anamnesisCheck = anamnesisCheck1
		
		anamnesisChecksValue.save()
		
		anamnesisChecksValue1 = anamnesisChecksValue;
	}
	
	def setUpTraining1(){
		def training = new Training()
		training.id=1L;
		training.name = 'traning1'
		training.trainingDate = new Date(new Date().getTime()+24*60*60*1000)
		training.timeStart = new Date(new Date().getTime()+24*60*60*1000)
		training.timeEnd = new Date(new Date().getTime()+24*60*60*1000+120*60*1000)
		training.save()
		training1 = training;
	}

	
	def setUpOsceDay1(){
		def osceDay = new OsceDay()
		osceDay.osceDate = new Date(new Date().getTime()+24*60*60*1000)
		osceDay.save()
		osceDay1 = osceDay;
		
	}
	def setUpOsceDays(){
		osce1=new OsceDay();
		osce1.id=1L
		osce1.osceDate=new Date(new Date().getTime()+24*60*60*1000);
		def osce = Osce.findByOrigId(1L);
		osce1.osce = osce;
		
		osce2=new OsceDay();
		osce2.id=2L
		osce2.osceDate=new Date(new Date().getTime()+12*60*60*1000);
		osce2.osce = osce;
		
		osce3=new OsceDay();
		osce3.id=3L
		osce3.osceDate=new Date(new Date().getTime()+15*60*60*1000);	
		osce3.osce = osce;
		
		osce1.save();
		osce2.save();
		osce3.save();
		
		assertTrue 3 == local.OsceDay.findAll().size();
	
	}
	
	def setUpOsces(){
		osce = new Osce();
		
		osce.id=1L;
		
		osce.origId = 1L;
		osce.semester = semester;
		osce.studyYear = 3;
		osce.numberRooms = 16;
		osce.name = "Test 1";
		osce.save();
	}
	
	def setUpSemesters(){
		semester = new Semester();
		semester.origId = 1L;
		semester.semester = 0;
		semester.calYear = 2011;
		
		semester.save();
	}

	def setUpTrainingDays(){


		training2=new Training();
		training2.id=2L
		training2.name="bbbbb"
		training2.trainingDate = new Date(new Date().getTime()+12*60*60*1000)
		training2.timeStart = new Date(new Date().getTime()+7*60*60*1000)
		training2.timeEnd = new Date(new Date().getTime()+7*60*60*1000+120*60*1000)
		
		def semester = Semester.findByOrigId(1L);
		training2.semester = semester;

		training3=new Training();
		training3.id=3L
		training3.name="ccc"
		training3.trainingDate = new Date(new Date().getTime()+8*60*60*1000)
		training3.timeStart = new Date(new Date().getTime()+9*60*60*1000)
		training3.timeEnd = new Date(new Date().getTime()+9*60*60*1000+120*60*1000)
		
		training3.semester = semester;
		
		training2.save();
		training3.save();
		
		
		assert 2 == local.Training.findAll().size();
	
	}
	def setUpTrainingDays1(){


		training1=new Training();
		training1.id=4L
		training1.name="aaaa"
		training1.trainingDate = new Date(new Date().getTime()+12*60*60*1000)
		training1.timeStart = new Date(new Date().getTime()+8*60*60*1000)
		training1.timeEnd = new Date(new Date().getTime()+8*60*60*1000+120*60*1000)
	
		training1.semester = semester;
		training1.save();
	}
	
	def setUpPatientLnSemester(){
		patientInSemester =new PatientlnSemester();
		patientInSemester.standardizedPatient =standardizedPatient1;
		patientInSemester.acceptedOsceDay=[osce1]
		patientInSemester.acceptedTraining=[training2]
		patientInSemester.accepted=true;
		def semester = Semester.findByOrigId(1L);
		patientInSemester.semester = semester;
		
		patientInSemester.save();
	
	
	}
	
	def setUpEmptyPatientLnSemester(){
		emptyPatientInSemester =new PatientlnSemester();
		emptyPatientInSemester.standardizedPatient =standardizedPatient2;
		emptyPatientInSemester.acceptedOsceDay=[]
		emptyPatientInSemester.acceptedTraining=[]
		emptyPatientInSemester.accepted=false;
		
		def semester = Semester.findByOrigId(1L);
		emptyPatientInSemester.semester = semester;
		
		emptyPatientInSemester.save();
	
	
	}
	
	

	def setupEmail(def prefix){
		def email = new Emails();
		email.sendDate = new Date();
		email.receiver = "${prefix}marvin@jserver.cn";
		email.content = "${prefix} this comment is for test";
		email.subject = "${prefix} subject";
		email.sent = "${prefix}sqq@jserver.cn";
		email.save();
		return email;
	}
}
