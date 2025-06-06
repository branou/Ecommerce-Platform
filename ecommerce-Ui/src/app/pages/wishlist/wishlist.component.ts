import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-wishlist',
  imports: [CommonModule],
  templateUrl: './wishlist.component.html',
  styleUrl: './wishlist.component.scss',
  standalone: true
})
export class WishlistComponent {
  products = [
    {
      id: 1,
      name: 'EVAWIN ROSAWIN® GEL NETTOYANT MDZ',
      description: '200 ml',
      price: 149,
      stock: true,
      imageUrl: 'https://parapharma.ma/34506-medium_default/evawin-rosawin-gel-nettoyant-mdz-200ml.webp',
    },
    {
      id: 2,
      name: 'SVR AMPOULE B3 REPULPANT ET ANTI-RIDES',
      description: '30 ml',
      price: 259,
      stock: true,
      imageUrl: 'https://parapharma.ma/31421-medium_default/svr-ampoule-b-30ml.webp',
    },
    {
      id: 3,
      name: 'SVR SPIRIAL SPRAY ANTI- TRANSPIRANT',
      description: '75 ml',
      price: 95.0,
      stock: false,
      imageUrl: 'https://parapharma.ma/31416-medium_default/svr-spirial-spray-anti-transpirant-75ml.webp',
    },
    {
      id: 4,
      name: 'Beliflor SHAMPOOING ANTIPELLICULAIRE',
      description: '250 ml',
      price: 90,
      stock: true,
      imageUrl: 'https://parapharma.ma/7663-medium_default/beliflor-shampooing-antipelliculaire-250ml.webp',
    },
  ];
  removeProduct(product: any) {
    this.products = this.products.filter(p => p !== product);
  }

}
