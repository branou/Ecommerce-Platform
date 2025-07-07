import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  imports: [FormsModule, CommonModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss',
  standalone: true
})
export class SignupComponent {
  constructor(private router: Router) {
  }
  formData = {
    firstname: '',
    lastname: '',
    email: '',
    password: '',
  };

  onSubmit(form: any) {
    this.router.navigate(['/login'])
    if (form.valid) {
      console.log('Login data:', this.formData);
      // Call your service here
    }
  }
}
