import {Component} from '@angular/core';
import { RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-payment-failed',
  imports: [CommonModule,
    FormsModule,
  RouterModule],
  templateUrl: './payment-failed.component.html',
  styleUrl: './payment-failed.component.scss'
})
export class PaymentFailedComponent {
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
