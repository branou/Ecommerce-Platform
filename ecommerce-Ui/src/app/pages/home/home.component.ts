import { Component } from '@angular/core';
import {Product} from '../../core/interfaces/model';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
  standalone: true
})
export class HomeComponent {
  constructor(private router : Router) {

  }
  navigateToProductDetails(productId: number) {
    this.router.navigate(['/product', productId]);
  }
  navigateToProducts(){
    this.router.navigate(['/products']);
  }
  products : Product[]=[
    {
      id: 1,
      name: 'EVAWIN ROSAWIN® GEL NETTOYANT MDZ 200ml',
      description: '200 ml',
      price: 149,
      stock: true,
      imageUrls: ['https://parapharma.ma/34506-medium_default/evawin-rosawin-gel-nettoyant-mdz-200ml.webp']
    },
    {
      id: 2,
      name: 'Cattier GYNEA HYGIENNE INTIME 200ml',
      description: '200 ml',
      price: 100,
      stock: true,
      imageUrls: ['https://parapharma.ma/46273-medium_default/cattier-gynea-hygienne-intime.webp']
    },
    {
      id: 3,
      name: 'Biokap Huile nourrissante 125ml',
      description: '125 ml',
      price: 195,
      stock: true,
      imageUrls: ['https://parapharma.ma/32808-medium_default/biokap-huile-nourrissante-et-reparatrice-cheveux-secs-125-ml.webp']
    },
    {
      id: 4,
      name: 'SVR SUN SECURE LAIT BIODEGRADABLE SPF 50+ 250ml',
      description: '250 ml',
      price: 289,
      stock: true,
      imageUrls: ['https://parapharma.ma/36258-medium_default/svr-sun-secure-lait-biodegradable-spf-50-200ml.webp']
    }
  ];
}
