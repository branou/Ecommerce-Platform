import { Component } from '@angular/core'
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
@Component({
  selector: 'app-signin',
  imports: [CommonModule,
    FormsModule],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.scss',
  standalone: true
})
export class SigninComponent {
  formData = {
    email: '',
    password: '',
  };

  constructor(private route : Router) {
  }

  onSubmit(form: any) {
    this.route.navigate(['/products']);
  }
}
