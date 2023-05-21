package com.tasdiqdewan.compose_library.composables.movie_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val history = rememberSaveable { mutableListOf<String>() }

    SearchBar(
        query = query,
        onQueryChange = { value -> query = value  },
        onSearch = { query ->
            if(query.isNotEmpty()) {
                history.add(query)
                active = false
                onSearch(query)
            }
        },
        active = active,
        onActiveChange = { active = it },
        enabled = true,
        placeholder = { Text("Search Movies") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        trailingIcon = {
            if(active) {
                IconButton(
                    onClick = {
                        if(query.isNotEmpty()) {
                            query = ""
                        }
                        else {
                            active = false
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                    )
                }
            }
        },
        modifier = modifier
    ) {
        history.filter { it.contains(query) }.forEach { searchQuery ->
            Text(
                text = searchQuery,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 4.dp)
                    .clickable(enabled = true) {
                        query = searchQuery
                    }
            )
        }
    }
}

@Preview
@Composable
fun SearchPreview() {
    MaterialTheme {
        Search(
            onSearch = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}