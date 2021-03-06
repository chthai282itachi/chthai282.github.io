package sp_portal

import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.json.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate


class OsceSyncController extends MainController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	private static final log = LogFactory.getLog(this)

    def syncJson(){
		log.info("user sync json")
        String jsonData = params.data;		
		def jsonObject = jsonToGroovy(jsonData);
        sync(jsonObject)
        
    }


    def jsonToGroovy(json){
		json = preProcessData(json);
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceSyncController Method jsonToGroovy(json) entered json : "+json)
		}
		log.info("parse json to jsonObject")
        def jsonObject = JSON.parse(json);	
	
        return jsonObject ;
    }

    private void importMessage(def oneMsg, def key){
        oneMsg << ["key":key]

    }
	def test(){

    } 
	
	private String preProcessData(String jsonStr){
		jsonStr = jsonStr.replaceAll("\"semester\":\"HS\"", "\"semester\":0");
		jsonStr = jsonStr.replaceAll("\"semester\":\"FS\"", "\"semester\":1");
		jsonStr = jsonStr.replaceAll("\"semester\":\"FED\"", "\"semester\":2");
		
		jsonStr = jsonStr.replaceAll("\"studyYear\":\"SJ1\"", "\"studyYear\":0");
		jsonStr = jsonStr.replaceAll("\"studyYear\":\"SJ2\"", "\"studyYear\":1");
		jsonStr = jsonStr.replaceAll("\"studyYear\":\"SJ3\"", "\"studyYear\":2");
		jsonStr = jsonStr.replaceAll("\"studyYear\":\"SJ4\"", "\"studyYear\":3");
		jsonStr = jsonStr.replaceAll("\"studyYear\":\"SJ5\"", "\"studyYear\":4");
		jsonStr = jsonStr.replaceAll("\"studyYear\":\"SJ6\"", "\"studyYear\":5");
		
		jsonStr = jsonStr.replaceAll("\"osceStatus\":\"OSCE_NEW\"", "\"osceStatus\":0");
		jsonStr = jsonStr.replaceAll("\"osceStatus\":\"OSCE_BLUEPRINT\"", "\"osceStatus\":1");
		jsonStr = jsonStr.replaceAll("\"osceStatus\":\"OSCE_GENRATED\"", "\"osceStatus\":2");
		jsonStr = jsonStr.replaceAll("\"osceStatus\":\"OSCE_FIXED\"", "\"osceStatus\":3");
		jsonStr = jsonStr.replaceAll("\"osceStatus\":\"OSCE_CLOSED\"", "\"osceStatus\":4");
		
		jsonStr = jsonStr.replaceAll("\"security\":\"SIMPLE\"", "\"security\":0");
		jsonStr = jsonStr.replaceAll("\"security\":\"FEDERAL_EXAM\"", "\"security\":1");
		
		jsonStr = jsonStr.replaceAll("\"osceSecurityTypes\":\"simple\"", "\"osceSecurityTypes\":0");
		jsonStr = jsonStr.replaceAll("\"osceSecurityTypes\":\"federal\"", "\"osceSecurityTypes\":1");
		
		jsonStr = jsonStr.replaceAll("\"patientAveragePerPost\":\"a1\"", "\"patientAveragePerPost\":1");
		jsonStr = jsonStr.replaceAll("\"patientAveragePerPost\":\"a2\"", "\"patientAveragePerPost\":2");
		jsonStr = jsonStr.replaceAll("\"patientAveragePerPost\":\"a3\"", "\"patientAveragePerPost\":3");
		jsonStr = jsonStr.replaceAll("\"patientAveragePerPost\":\"a4\"", "\"patientAveragePerPost\":4");
		jsonStr = jsonStr.replaceAll("\"patientAveragePerPost\":\"a5\"", "\"patientAveragePerPost\":5");
		jsonStr = jsonStr.replaceAll("\"patientAveragePerPost\":\"a6\"", "\"patientAveragePerPost\":6");
		jsonStr = jsonStr.replaceAll("\"patientAveragePerPost\":\"a7\"", "\"patientAveragePerPost\":7");
		jsonStr = jsonStr.replaceAll("\"patientAveragePerPost\":\"a8\"", "\"patientAveragePerPost\":8");
		
		
		
		
		
		return jsonStr;
			
	}
	

			
	private void savePatient(def newPatient,def patient,def locale,key,oneMsg){
		//local.StandardizedPatient newPatient = new local.StandardizedPatient();
	
		newPatient.origId = patient.id;
		if(patient.gender!=null && patient.gender != JSONObject.NULL){
			newPatient.gender=patient.gender;
		}
		if(patient.preName!=null && patient.preName != JSONObject.NULL){
			newPatient.preName=patient.preName;
		
		}
		if(patient.street!=null && patient.street != JSONObject.NULL){
			newPatient.street=patient.street;
		
		}
		if(patient.city!=null && patient.city != JSONObject.NULL){
			newPatient.city=patient.city;
		
		}
		if(patient.postalCode!=null && patient.postalCode != JSONObject.NULL){
			newPatient.postalCode=patient.postalCode;
		
		}
		if(patient.telephone!=null && patient.telephone != JSONObject.NULL){
			newPatient.telephone=patient.telephone;
		
		}
		if(patient.telephone2!=null && patient.telephone2 != JSONObject.NULL){
			newPatient.telephone2=patient.telephone2;
		
		}
		if(patient.mobile!=null && patient.mobile != JSONObject.NULL){
			newPatient.mobile=patient.mobile;
		
		}
		if(patient.height!=null && patient.height != JSONObject.NULL){
			newPatient.height=patient.height;
		
		}
		if(patient.weight!=null && patient.weight != JSONObject.NULL){
			newPatient.weight=patient.weight;
		
		}
		if(patient.immagePath!=null && patient.immagePath != JSONObject.NULL){
			newPatient.immagePath=patient.immagePath;
		
		}
		if(patient.videoPath!=null && patient.videoPath != JSONObject.NULL){
			newPatient.videoPath=patient.videoPath;
		
		}
		if(patient.birthday!= JSONObject.NULL){
			Date birthday = convertToDate(patient.birthday);
			if(birthday){
				newPatient.birthday=new LocalDate(birthday.getTime());
			}
		
		}
		if(patient.email!=null && patient.email != JSONObject.NULL){
			newPatient.email=patient.email;
		
		}
		if(patient.maritalStatus!=null && patient.maritalStatue != JSONObject.NULL){
			newPatient.maritalStatus=patient.maritalStatus;
		
		}
		if(patient.workPermission!=null && patient.workPermission != JSONObject.NULL){
			newPatient.workPermission=patient.workPermission;
		
		}
		if(patient.status!=null && patient.status != JSONObject.NULL){
			newPatient.status=4;
		
		}
		if(patient.socialInsuranceNo!=null && patient.scoialInsuranceNo != JSONObject.NULL){
			newPatient.socialInsuranceNo=patient.socialInsuranceNo;
		}
		try{
			newPatient.save(flush:true);
		}catch(Exception e){
			log.trace("newPatient data Exception " +e.message());
		}
			boolean isCanSaveUser = true;
		
		if (newPatient.hasErrors()) {	
			isCanSaveUser = false;
			key = message(code: 'default.error.standardizedPatient.message',args: [newPatient.preName.toString()],locale: locale)
			importMessage(oneMsg,key)
		}	
		
		if(isCanSaveUser == true){
			createUser(newPatient);
		}
	}
	
	 private def createUser(standardizedPatient){
		if(log.isTraceEnabled()){
			log.trace(">> In class DataImportExportController Method createUser entered standardizedPatient : "+standardizedPatient + "  jsonData : "+jsonData)
		}
        def x =new User();
			x.userName= standardizedPatient.email;
			x.passwordHash= MainController.encodePassword(""+standardizedPatient.socialInsuranceNo,x.userName);
			x.userEmail=standardizedPatient.email;
			x.standardizedPatient=standardizedPatient;
			x.isActive=true;
			def roles = [];
			roles.add(Role.findByRoleName("USER_ROLE"));
			x.roles = roles;
			x.save(flush:true);		

    }

	
	private void savePatientInSemester(def newpatientInSemester, def semester,def patient,def accepted){
		//local.PatientlnSemester newpatientInSemester = new local.PatientlnSemester();
		if(semester!=null){
			newpatientInSemester.semester=semester
		}
		if(patient!=null){
			newpatientInSemester.standardizedPatient=patient
		
		}
		if(accepted!=null){
			newpatientInSemester.accepted=accepted
		}
	
		newpatientInSemester.save(flush:true);
	}


    /**
     * Receive OSCEDay, Training, SP-Data via JSONObject from OSCE Manager.
     * This method is called after the data remotely sent to SP Portal has
     * been pre processed in a JSON object.
     */
    def sync(data){
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceSyncController Method sync entered data : "+data)
		}
        def key = [];
		def oneMsg = [];		

        if(data){
            //get locale from osce
            def locale = Locale.GERMAN;
			if(data.language != JSONObject.NULL && !data.language.equals("")){				
				def language = data.language.toString()
				locale = new Locale(language)
            }
			
			// synchronize Patitent
			for(int i = 0; i< data.patients.size();i++){
				def syncPatient = data.patients[i];
				def localPatient = local.StandardizedPatient.findByOrigId(syncPatient.id);
				if(!localPatient){
					localPatient = new local.StandardizedPatient();
				}
				savePatient(localPatient,syncPatient,locale,key,oneMsg);
			}
			
			//Synchronise Semester
			log.info("Synchronise Semester");
			for(int i = 0 ; i<data.semesters.size();i++){
				def syncSemester = data.semesters[i];
				def localSemester = local.Semester.findByOrigId(syncSemester.id);
				if(!localSemester){
					localSemester = new local.Semester();
					if(syncSemester.id != JSONObject.NULL){
						localSemester.origId= syncSemester.id;
					}
					
					if(syncSemester.semester != JSONObject.NULL){
						localSemester.semester= syncSemester.semester;
					}
					
					if(syncSemester.calYear != JSONObject.NULL){
						localSemester.calYear= syncSemester.calYear;
					}				
					localSemester.save(flush:true);
					key = message(code: 'default.notFound.Semester.message', args: [syncSemester.id.toString()],locale: locale)
					importMessage(oneMsg,key)
					
				} else {
					if(syncSemester.semester != JSONObject.NULL){
						localSemester.semester= syncSemester.semester;
					}
					
					if(syncSemester.calYear != JSONObject.NULL){
						localSemester.calYear= syncSemester.calYear;
					}
					localSemester.save(flush:true);
					key = message(code: 'default.found.Semester.message', args: [syncSemester.id.toString()],locale: locale)
					importMessage(oneMsg,key)
				}
				
			}
	
			//Synchronise Osce
			log.info("Synchronise Osce");
			for(int i = 0 ; i<data.osces.size();i++){
				def osces = data.osces[i];
				def osce = local.Osce.findByOrigId(osces.id);
				
				if(!osce){
					osce=new local.Osce();
					
					if(osces.id != JSONObject.NULL){
						osce.origId= osces.id;
					}
					
					if(osces.studyYear != JSONObject.NULL){
						osce.studyYear=osces.studyYear
					}

					if(osces.name != JSONObject.NULL){
						osce.name=osces.name
					}
					
					if(osces.numberRooms != JSONObject.NULL){
						osce.numberRooms=osces.numberRooms
					}
					
					if(osces.semester != JSONObject.NULL){
						local.Semester oscesSemester = local.Semester.findByOrigId(osces.semester);
						
						if(oscesSemester){
							osce.semester=oscesSemester
						}
					}
					
					if(osces.copiedOsce != JSONObject.NULL){
					local.Osce copiedOsce = local.Osce.findByOrigId(osces.copiedOsce);
						if(copiedOsce){
							osce.copiedOsce=copiedOsce
						}
					}

					osce.save(flush:true);
					key = message(code: 'default.notFound.Osce.message', args: [osces.id.toString()],locale: locale)
					importMessage(oneMsg,key)
					
				}else{
					if(osces.id != JSONObject.NULL){
						osce.origId= osces.id;
					}
					
					if(osces.numberRooms != JSONObject.NULL){
						osce.numberRooms=osces.numberRooms
					}
					
					if(osces.studyYear != JSONObject.NULL){
						osce.studyYear=osces.studyYear
					}
					
					if(osces.name != JSONObject.NULL){
						osce.name=osces.name
					}
					
					if(osces.semester != JSONObject.NULL){
						local.Semester oscesSemester = local.Semester.findByOrigId(osces.semester);
						
						if(oscesSemester){
							osce.semester=oscesSemester
						}
					}
					
					if(osces.copiedOsce != JSONObject.NULL){
					local.Osce copiedOsce = local.Osce.findByOrigId(osces.copiedOsce);
						if(copiedOsce){
							osce.copiedOsce=copiedOsce
						}
					}
					osce.save(flush:true);
					key = message(code: 'default.found.Osce.message', args: [osces.id.toString()],locale: locale)
					importMessage(oneMsg,key)
				}
			}
			//Synchronise PatientInSemester
			for(int i = 0; i< data.patientInSemester.size();i++){
				def patientInSemesters = data.patientInSemester[i];
				//def pis = local.PatientInSemester.findAll("from PatientInSemester as p where p.semester=? and p.standardizedPatient=?",[patientInSemesters.semester,patientInSemesters.standardizedPatient]);
				def semester = local.Semester.findByOrigId(patientInSemesters.semester);
				def patient = local.StandardizedPatient.findByOrigId(patientInSemesters.standardizedPatient);
				def pis;
				if(semester != null && patient != null){
					pis = local.PatientlnSemester.findBySemesterAndStandardizedPatient(semester,patient);
				
					if(!pis){
						pis = new local.PatientlnSemester();
					}
					savePatientInSemester(pis,semester,patient,patientInSemesters.accepted);	
				}
			} 
			
            //Synchronise OsceDay
			log.info("Synchronise OsceDay")
            for(int i = 0; i<data.osceDay.size();i++){
                def day = data.osceDay[i];
                if(day.osceDate != null && !day.osceDate.equals("")){
                    def date = convertToDate(day.osceDate);
                    def osceDay = local.OsceDay.findOsceDaysByOsceDate(date);
                    if(!osceDay){
                        osceDay = new local.OsceDay();
						if(day.timeStart != JSONObject.NULL && !day.timeStart.equals("")){
							osceDay.timeStart=convertToDate(day.timeStart)
						}
						
						if(day.osce != JSONObject.NULL){
							def osce=local.Osce.findByOrigId(day.osce)
							if(osce){
								osceDay.osce=osce
							}
						}
							
						if(day.timeEnd != JSONObject.NULL && !day.timeEnd.equals("")){
							osceDay.timeEnd=convertToDate(day.timeEnd)
						}
						
						if(day.lunchBreakStart != JSONObject.NULL && !day.lunchBreakStart.equals("")){
							osceDay.lunchBreakStart=convertToDate(day.lunchBreakStart)
						}
						
						if(day.lunchBreakAfterRotation != JSONObject.NULL){
							osceDay.lunchBreakAfterRotation=day.lunchBreakAfterRotation
						}
						
						if(day.value != JSONObject.NULL){
							osceDay.value=day.value
						}
						
                        osceDay.osceDate = date;
                        osceDay.save(flush:true);
                        key = message(code: 'default.notFound.OsceDay.message', args: [convertToString(date)],locale: locale);
                        importMessage(oneMsg,key)
                    }else{
                        key = message(code: 'default.found.OsceDay.message', args: [convertToString(date)],locale: locale)
                        importMessage(oneMsg,key)
                    }
                }else{

                   key = message(code: 'default.cannotSave.OsceDay.message',locale: locale)
                    importMessage(oneMsg,key)
                }
            }
			

            //Synchronise Training
			log.info("Synchronise Training")
            for(int i = 0; i<data.trainings.size();i++){
                def jsonTraining = data.trainings[i];

                if(jsonTraining.trainingDate != JSONObject.NULL){
                    def start= convertToDate(jsonTraining.timeStart);
                    def date = convertToDate(jsonTraining.trainingDate);
                    def name = jsonTraining.name;
                    def training = null;
                    if(date){
                        if(start){
                            training = local.Training.findTraningsByDateAndStart(date,start);
                        }else{
                            training = local.Training.findTrainingsByDateAndName(date,name);
                        }
                        if(!training){
                            if(jsonTraining.name && jsonTraining.trainingDate && !jsonTraining.name.equals("") && !jsonTraining.trainingDate.equals("")){
                                training = new local.Training();
                                training.name = jsonTraining.name;
                                training.trainingDate = convertToDate(jsonTraining.trainingDate);
                                training.timeStart = convertToDate(jsonTraining.timeStart);
                                training.timeEnd = convertToDate(jsonTraining.timeEnd);
								if(jsonTraining.semester != JSONObject.NULL){
									def semesterTraining = local.Semester.findByOrigId(jsonTraining.semester);
									if(semesterTraining){
										training.semester=semesterTraining
									}
									
								}
                                training.save(flush:true);
                                if(start){
                                    key = message(code: 'default.notFound.Training.message', args: [convertToString(start)],locale: locale)
                                }else{
                                    key = message(code: 'default.notFound.Training.message', args: [convertTrainingDateToString(date)],locale: locale)
                                }
                            }else{
                                key = message(code: 'default.cannotSave.Training.message',locale: locale)
                            }
                            importMessage(oneMsg,key)
                        }else{
                            if(start){
                                key = message(code: 'default.foundExist.Training.message', args: [convertToString(start)],locale: locale)
                            }else{
                                key = message(code: 'default.foundExist.Training.message', args: [convertTrainingDateToString(date)],locale: locale)
                            }
                            importMessage(oneMsg,key)

                        }
                    }else{
                        key = message(code: 'default.cannotSave.Training.message',locale: locale)
                        importMessage(oneMsg,key)
                    }
                }
			}
        }
	



            //set json date and send to OSCE
			log.info("set json date of DMZ and send to OSCE")
			
           

            def oneMsgJson = oneMsg as JSON;
			if(log.isDebugEnabled()){
				log.debug("message : "+oneMsgJson)
			}


            
            def jsonStr = "{\"message\" : "+oneMsgJson+"}"

            response.setCharacterEncoding("UTF-8");
			if(log.isDebugEnabled()){
				log.debug("render jsonStr : "+jsonStr)
			}
			println("-------------_________error json "+jsonStr);
            render text:jsonStr ,contentType:"application/json",encoding:"UTF-8"
		   

    }
	
	def getSyncJson(){
			def osceDayList = local.OsceDay.list();
            def trainingList = local.Training.list();
            def patientImSemesterList = local.PatientlnSemester.list();
			String osceDayListJson = getOsceDayJson(osceDayList);
            String trainingListJson =getTrainingJson(trainingList);
            String patientImSemesterListJson = getPatientInSemesterJson(patientImSemesterList)
			
			def jsonStr = "{\"osceDay\" :"+osceDayListJson+",\"trainings\" : "+trainingListJson + ",\"patientInSemester\" : "+patientImSemesterListJson + "}"
//			for(local.OsceDay day:osceDayList){
//					day.delete();
//			}
			
//			for(local.PatientlnSemester pl:patientImSemesterList){
//					pl.delete();
//			}
			
			def osce=local.Osce.list();
			
//			for(local.Osce o: osce){
//				o.delete();
//			}
			
//			for(local.Training train:trainingList){
//				train.delete();
//			}

			def sm =local.Semester.findAll();
			println("############# sm " +sm);
//			for(local.Semester sme: sm){
//				sme.delete();
//			}
			

			response.setCharacterEncoding("UTF-8");
			if(log.isDebugEnabled()){
				log.debug("render jsonStr : "+jsonStr)
			}
			
			println("-------------_________return json "+jsonStr);
            render text:jsonStr ,contentType:"application/json",encoding:"UTF-8"
	} 
	
	
    

    /**
     * get the response json data of PatientInSemester
     */
    private String getPatientInSemesterJson(def patientImSemesterList){
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceSyncController Method getPatientInSemesterJson entered patientImSemesterList : "+patientImSemesterList)
		}
        String patientImSemesterListJson = "[";
        int count =0;
        for(local.PatientlnSemester semeter : patientImSemesterList){
            count ++;
            String patientJson ="{\"standarizedPatientId\":"+semeter.standardizedPatient.getOrigId()+",";
            patientJson += "\"acceptedTrainings\":"+ getTrainingJson(semeter.acceptedTraining)+",";
            patientJson += "\"acceptedOsce\":"+ getOsceDayJson(semeter.acceptedOsceDay)+",";
            patientJson += "\"accepted\":"+ semeter.accepted+",";
			patientJson += "\"semester\":"+ semeter.semester.getOrigId();
			
            patientImSemesterListJson +=patientJson+"}";
            if(count != patientImSemesterList.size()){
                patientImSemesterListJson += ",";
            }

        }
        patientImSemesterListJson +="]";
		if(log.isTraceEnabled()){
			log.trace("<< In class OsceSyncController Method getPatientInSemesterJson return patientImSemesterListJson : "+patientImSemesterListJson)
		}
        return patientImSemesterListJson;

    }
	

    /**
     * get the response Json data of OsceDay
     */
    private String getOsceDayJson(def osceDayList){
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceSyncController Method getOsceDayJson entered osceDayList : "+osceDayList)
		}
        String osceDayListJson = "[";

        int i = 0;
        for(  local.OsceDay day : osceDayList){
            i++
			
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			
			sb.append("\"osceDate\":"); 
			if(day.getOsceDate()){
				sb.append("\""+convertToString(day.getOsceDate())+"\"");
			}else{
				sb.append("null");
			}
			sb.append(",");
			
			sb.append("\"timeStart\":");
			if(day.getTimeStart()){
				sb.append("\""+convertToString(day.getTimeStart())+"\"");
			}else{
				sb.append("null");
			}
			sb.append(",");
			
			sb.append("\"timeEnd\":");
			if(day.getTimeEnd()){
				sb.append("\""+convertToString(day.getTimeEnd())+"\"");
			}else{
				sb.append("null");
			}			
			sb.append(",");
			
			sb.append("\"lunchBreakStart\":");
			if(day.getLunchBreakStart()){
				sb.append("\""+convertToString(day.getLunchBreakStart())+"\"");
			}else{
				sb.append("null");
			}	
			sb.append(",");
			
			sb.append("\"lunchBreakAfterRotation\":"+day.getLunchBreakAfterRotation());
			sb.append(",");
			sb.append("\"osce\":");
			if(day.getOsce()){
				sb.append(day.getOsce().getOrigId().toString());
			}else{
				sb.append("null");
			}
			sb.append(",");
			sb.append("\"value\":"+day.getValue());
			
			sb.append("}");
			
			
            osceDayListJson+=sb.toString();
            if(i != osceDayList.size()){
                osceDayListJson+=",";
            }
        }
        osceDayListJson+="]";
		if(log.isTraceEnabled()){
			log.trace("<< In class OsceSyncController Method getOsceDayJson return osceDayListJson : "+osceDayListJson)
		}
        return osceDayListJson;
    }

    /**
     * get the response Json data of Training
     */
    private String getTrainingJson(def trainingList){
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceSyncController Method getTrainingJson entered trainingList : "+trainingList)
		}
        String trainingListJson = "[";

        int i = 0;
        for( local.Training training : trainingList){
			i++  
				   
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			String name = "";
			if(training.getName() !=null ){
				name = training.getName();
			}
			sb.append("\"name\" : "+"\""+name+"\",");
			sb.append("\"trainingDate\" :");
			if(training.getTrainingDate() != null){
				sb.append("\""+convertToString(training.getTrainingDate())+"\"");
			}else{
				sb.append("null");
			}
			sb.append(",");
			
			sb.append("\"timeStart\":");
			if(training.getTimeStart() != null){
				sb.append("\""+convertToString(training.getTimeStart())+"\"");
			}else{
				sb.append("null");
			}
			sb.append(",");
			
			sb.append("\"timeEnd\":");
			if(training.getTimeEnd() != null){
				sb.append("\""+convertToString(training.getTimeEnd())+"\"");
			}else{
				sb.append("null");
			}
			sb.append(",");
			sb.append("\"semester\":"+training.getSemester().getOrigId());
			sb.append("}");
	   
            trainingListJson+=sb.toString();
            if(i != trainingList.size()){
                trainingListJson+=",";
            }
        }
        trainingListJson+="]";
		if(log.isTraceEnabled()){
			log.trace("<< In class OsceSyncController Method getTrainingJson return trainingListJson : "+trainingListJson)
		}
        return trainingListJson;

    }


    /**
     *The date of the format string into "yyyy-MM-dd 'T' HH: MM: ss 'Z'" format
     */
    private Date convertToDate(String dateStr){
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceSyncController Method convertToDate entered dateStr : "+dateStr)
		}
        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Date date=null;
        try {
            if(dateStr && !dateStr.equals("")){
                date = sdf.parse(dateStr);
            }
			
        } catch (ParseException e) {
            e.printStackTrace();
        }
		if(log.isTraceEnabled()){
			log.trace("<< In class OsceSyncController Method convertToDate return date : "+date)
		}
        return date;
    }

    /**
     * Time is converted to a string
     **/
    private String convertToString(Date date){
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceSyncController Method convertToString entered date : "+date)
		}
        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        String dateStr="";
        try {
            if(date){
                dateStr = sdf.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		if(log.isTraceEnabled()){
			log.trace("<< In class OsceSyncController Method convertToString return dateStr : "+dateStr)
		}
        return dateStr;
    }

    /**
     * Time is converted to a string
     **/
    private String convertTrainingDateToString(Date date){
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceSyncController Method convertTrainingDateToString entered date : "+date)
		}
        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        String dateStr=null;
        try {
            if(date){
                dateStr = sdf.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		if(log.isTraceEnabled()){
			log.trace("<< In class OsceSyncController Method convertTrainingDateToString return dateStr : "+dateStr)
		}
        return dateStr;
    }

}
