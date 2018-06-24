window.onload=()=> {
	let login=document.getElementById('loginbtn');
	var signup=document.getElementById('signupbtn');
	login.onclick=()=> {
		alert("hi");
	}

	var inputs={
		"fName":"Enter First name",
		"mName":"Enter Middle name",
		"lName":"Enter Last name",
		"address":"Enter Address"
	}
	var inputs2={
		"bday":"Birthday",
		"gender":"Enter gender",
		"email":"Enter email-add",
		"user":"username"

	}
	var inputs3={
		"phone":"Enter mobile number",
		"age":"Enter age"
	}

	signup.onclick=()=> {
		let body=document.body;
		createElement("div",'fullscreenpopup','','',body,'signupFormModal','','');
		let signupFormModal=document.getElementById('signupFormModal');
		createElement('div','fullscreenpopup_container','','',signupFormModal,'container','','');
		let container=document.getElementById('container');
		createElement('div','fullscreenpopup_content','','',container,'contentt','','');
		let content=document.getElementById('contentt');
		createElement('p','','','',content,'header','Applicants Form','');
		createElement('div','','','',content,'cont1','','');
		let cont1=document.getElementById('cont1');
		for (key in inputs) { createElement("input","input","text",inputs[key],cont1,key,'',''); }
		createElement('div','','','',content,'cont2','');
		let cont2=document.getElementById('cont2');
		for (key in inputs2) {
			let type='';
			switch(key) {
				case 'bday':
					type='date';
					break;
				case 'email':
					type='email';
					break;
				default:
					type='text';
					break;
			}
			createElement("input","input",type,inputs2[key],cont2,key,'',''); 
		}
		createElement('div','','','',content,'cont3','');
		let cont3=document.getElementById('cont3');
		for (key in inputs3) { createElement("input","input","number",inputs3[key],cont3,key,'',''); }
		createElement("select",'','',"'Select Date",cont3,"dateCont",'Select date',null);
		let date=document.getElementById('dateCont');
		createElement("option",'','','',date,'','Select date',null);
		doGetRequest('/project/dates',function(e) {
			for(let i in e) {
				createElement("option",'','','',date,'',e[i].month+" "+e[i].day+", "+e[i].year+" - "+e[i].day2,e[i].id);
			}
		});
		createElement('div','','','',content,'btns','','');
		var btns=document.getElementById('btns');
		createElement('a','button','','',btns,'submit','','');
		let submit=document.getElementById('submit');
		submit.href='#';
		createElement('a','close','','',btns,'close','','');
		let close=document.getElementById('close');
		close.href='#';
		submit.onclick=()=> {
			let fName=document.getElementById('fName').value;
			let mName=document.getElementById('mName').value;
			let lName=document.getElementById('lName').value;
			let address=document.getElementById('address').value;
			let age=document.getElementById('age').value;
			let bday=document.getElementById('bday').value;
			let email=document.getElementById('email').value;
			let phone=document.getElementById('phone').value;
			let user=document.getElementById('user').value;
			let date=document.getElementById('dateCont').value;
			let gender=document.getElementById('gender').value;
			let data="fName="+fName+"&mName="+mName+"&lName="+
			lName+"&address="+address+"&age="+age+"&bday="+bday+
			"&email="+email+"&phone="+phone+"&user="+user+"&date="+date+"&gender="+gender;
			console.log(data);
			if(fName!='' && mName!='' && lName!='' && address!='' && age!='' && bday!='' && email!='' && phone!='' && gender!='' && user!='' && data!='' && date!=null) {
				doPostRequest(data,"/project/signup",signupFormModal);
			}
			else {
				alert("Please fill up the form!");
			}
		}
		close.onclick=()=> {
			destroyElement(signupFormModal);
		}
		createElement('span','','','',submit,'span','OK','');
		close.innerHTML="close";
	}
}

createElement=(type,className,att1,att2,parent,id,text,value)=> {
	let elem=document.createElement(type);
	elem.className=className;
	elem.setAttribute('type', att1);
	elem.setAttribute('id',id);
	elem.setAttribute('placeholder', att2);
	elem.setAttribute('value',value);
	elem.setAttribute('required','true')
	elem.appendChild(document.createTextNode(text));
	parent.appendChild(elem);
}

destroyElement=(elem)=> {
	document.body.removeChild(elem);
}

doPostRequest=(data,url,elem)=> {
	let xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function() {
		if(xhr.readyState==4) {
			alert("Added");
			console.log(data);
			destroyElement(elem);
		}
	}
	xhr.open("POST",url,true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send(data);
}

doGetRequest=(url,callback)=> {
	let xhr=new XMLHttpRequest();
	xhr.onreadystatechange=()=> {
		if(xhr.readyState==4) {
			callback(JSON.parse(xhr.responseText));
		}
	}
	xhr.open('GET',url,true);
	xhr.send();
}
