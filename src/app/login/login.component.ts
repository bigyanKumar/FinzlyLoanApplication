import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup,Validator, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { ApiServicesService } from '../services/api-services.service';
import { AppConstant } from '../app.constant';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form !:FormGroup;

  constructor(private fb:FormBuilder,public router:Router,public apiservice:ApiServicesService,private toastr: ToastrService) { }
  public userSession=AppConstant.userSession
  ngOnInit(): void {
    if(localStorage.getItem('token')){
      this.router.navigateByUrl("dashboard")
    }
    this.form = this.fb.group({
        email:[null,[Validators.required]],
        password:[null,[Validators.required]]
    })

  }
  loginUser(){
      if(this.form.value.email!=null && this.form.value.password!=null){
        console.log(this.form.value)
        this.apiservice.login(this.form.value).subscribe((data:any)=>{
            this.userSession=data;
            console.log(this.userSession.token)
            localStorage.setItem('token',this.userSession.token||"");
            this.router.navigateByUrl("dashboard")
            this.toastr.success("Logged In Sucessfully!")
        },err=>{
          console.log(err)
          this.toastr.error("Server Error",err)
        });
        
      }else{
          console.log(console.log("error"))
          this.toastr.error("Bad Input.")
      }
  }

}
