import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-signup',
  imports: [FormsModule, CommonModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss',
  standalone: true
})
export class SignupComponent {
  formData = {
    email: '',
    password: '',
  };

  onSubmit(form: any) {
    if (form.valid) {
      console.log('Login data:', this.formData);
      // Call your service here
    }
  }
}
