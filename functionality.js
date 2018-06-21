window.onload=function() {
	var login=document.getElementById('loginbtn');
	var signup=document.getElementById('signupbtn');
	login.onclick=function() {
		let xhr=new XMLHttpRequest();
		xhr.open("GET","dates",true);
		xhr.send();
		xhr.onreadystatechange=function() {
			if(xhr.readyState==4) {
				let data=JSON.parse(xhr.responseText);
				for(key in data) {
					console.log(data[key].date);
				}
			}
		}
	}

	var inputs={
		"fName":"Enter First name",
		"mName":"Enter Middle name",
		"lName":"Enter Last name",
		"address":"Enter Address"
	}
	var inputs2={
		"age":"Enter age",
		"bday":"Birthday",
		"gender":"Enter gender",
		"email":"Enter email-add"
	}
	var inputs3={
		"phone":"Enter mobile number",
		"user":"username"
	}

	signup.onclick=function popup1(text) {
		let popup=document.createElement('div');
		popup.className='fullscreenpopup';
		let container=document.createElement('div');
		container.className='fullscreenpopup_container';
		popup.appendChild(container);
		let content=document.createElement('div');
		content.className='fullscreenpopup_content';
		container.appendChild(content);
		let p1=document.createElement('p');
		p1.appendChild(document.createTextNode("Applicant's Form"));
		content.appendChild(p1);
		let cont1=document.createElement('div');
		content.appendChild(cont1);
		for (key in inputs) { createElement("input","input","text",inputs[key],cont1,key); }
		let cont2=document.createElement('div');
		content.appendChild(cont2);
		for (key in inputs2) { createElement("input","input","text",inputs2[key],cont2,key); }
		let cont3=document.createElement('div');
		content.appendChild(cont3);
		for (key in inputs3) { createElement("input","input","text",inputs3[key],cont3,key); }
		var dateCont=createElement("select",null,null,null,content,"dateCont");
		// createElement("option",null,null,null,dateCont,null);
		// // createElement("option",null,null,null,dateCont,null);
		var p2=document.createElement('p');
		content.appendChild(p2);
		var p3=document.createElement('p');
		content.appendChild(p3);
		var button=document.createElement('a');
		button.href='#';
		button.className='button';
		var close=document.createElement('a');
		close.href='#';
		close.className='close';
		button.onclick=function() {
			let fName=document.getElementById('fName').value;
			let mName=document.getElementById('mName').value;
			let lName=document.getElementById('lName').value;
			let address=document.getElementById('address').value;
			let age=document.getElementById('age').value;
			let bday=document.getElementById('bday').value;
			let email=document.getElementById('email').value;
			let phone=document.getElementById('phone').value;
			let user=document.getElementById('user').value;
			let data="fName="+fName+"&mName="+mName+"&lName="+lName+"&address="+address;
			doPostRequest(data,"/project/signup");
		}
		close.onclick=function() {
			document.body.removeChild(popup);
		}
		p2.appendChild(button);
		p3.appendChild(close);
		var span=document.createElement('span');
		span.appendChild(document.createTextNode('OK'));
		button.appendChild(span);
		close.innerHTML="close";
		document.body.appendChild(popup);
	}
}

function createElement(type,className,att1,att2,parent,id) {
	let elem=document.createElement(type);
	elem.className=className;
	elem.setAttribute('type', att1);
	elem.setAttribute('id',id);
	elem.setAttribute('placeholder', att2);
	parent.appendChild(elem);
}


function doPostRequest(data,url) {
	let xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function() {
		if(xhr.readyState==4) {
			alert("You have succesfully signed up.");
			console.log(data);
		}
	}
	xhr.open("POST",url,true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send(data);
}
