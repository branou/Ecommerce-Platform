import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {FooterComponent} from '../footer/footer.component';
import {NavbarComponent} from '../navbar/navbar.component';

@Component({
  selector: 'app-default-layout',
  imports: [RouterOutlet,FooterComponent,NavbarComponent],
  templateUrl: './default-layout.component.html',
  styleUrl: './default-layout.component.scss'
})
export class DefaultLayoutComponent {

}
