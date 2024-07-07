import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';


import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Page } from '../../models/Page'
import { Product } from '../../models/Product';
import { ApiService } from '../../service/ApiService';

@Component({
  selector: 'app-product-listing',
  templateUrl: './product-listing.component.html',
  styleUrls: ['./product-listing.component.css']
})
export class ProductListingComponent implements OnInit {
 
  displayedColumns: string[] = ['id', 'name', 'description', 'rating', 'price', 'image'];
  // displayedColumns: string[] = ['id', 'name', 'description', 'rating', 'price', 'image', 'actions'];
  products: Product[] = [];
  page?: Page<Product>;
  pageSize = 30;
  currentPage = 0;
  searchQuery = '';

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(n? : string) {
    this.apiService.getProducts({ name: n,
       page: this.currentPage,
        size: this.pageSize, 
        sortType: 'id',
         order: 'ASC'})
    .subscribe((data: Page<Product>) => {
      this.page = data;
      this.products = data.content;
    })
  }

   onClickSearch() {
    this.currentPage = 0;
    this.loadProducts(this.searchQuery);
  }
}