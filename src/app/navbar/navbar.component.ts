import { Token } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router, RouterPreloader } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit{

  constructor(public router:Router,private toastr: ToastrService) { }

  public user:any;
  public login=true;
  ngOnInit(): void {
    if(localStorage.getItem('token')){
        this.user=localStorage.getItem("user")
        this.user=JSON.parse(this.user);
        this.login=false
    }else{
      this.router.navigateByUrl("login")
      this.login=true;
    }
    

  }
  signOut(){
    localStorage.clear()
    this.login=true
    this.router.navigateByUrl("login")
    this.toastr.success("Logged out Sucessfully!")
  }

}
