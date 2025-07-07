import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-client-place',
  imports: [],
  templateUrl: './client-place.component.html',
  styleUrl: './client-place.component.scss'
})
export class ClientPlaceComponent {
  constructor(private router : Router) {
  }

  gotopayment() {
    this.router.navigate(['/checkout'])
  }
}
