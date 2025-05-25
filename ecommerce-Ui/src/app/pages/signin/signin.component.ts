import { Component } from '@angular/core'
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
@Component({
  selector: 'app-signin',
  imports: [CommonModule,
    FormsModule],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.scss'
})
export class SigninComponent {
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
